package food.booking.app.storage.adapter.in.web;

import food.booking.app.storage.app.port.in.UploadFileCommand;
import food.booking.app.storage.app.port.in.UploadFileUseCase;
import food.booking.app.storage.app.port.in.exception.InvalidFileTypeException;
import food.booking.app.storage.app.port.in.exception.InvalidFileTypeException.InvalidFileTypeConstraintViolation;
import food.booking.app.storage.domain.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Upload file controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/storage")
class UploadFileController {

    private final FileWebMapper fileWebMapper;

    private final UploadFileUseCase uploadFileUseCase;

    private final static List<String> ALLOWED_TYPES = List.of("image/gif", "image/jpeg", "image/png");

    /**
     * Upload file
     *
     * @param file multipart file
     * @return upload file response
     */
    @PostMapping(path = "/uploadFile", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UploadedFileModel> uploadFile(@RequestParam("file") MultipartFile file) {
        log.debug("REST request to upload file: {}", file);
        if (hasWrongType(file)) {
            throw new InvalidFileTypeException(
                    "File has invalid type!",
                    new HashSet<>(List.of(new InvalidFileTypeConstraintViolation(file))));
        }
        UploadedFileModel model = executeFileUpload(file);
        return ResponseEntity.created(model.url()).body(model);
    }

    /**
     * Upload multiple files
     *
     * @param files multipart files
     * @return list of uploaded file responses
     */
    @PostMapping("/uploadMultipleFiles")
    ResponseEntity<List<UploadedFileModel>> uploadMultipleFiles(@RequestParam("file") List<MultipartFile> files) {
        log.debug("REST request to upload multiple files: {}", files);
        List<MultipartFile> invalidFiles = anyHasWrongType(files);
        if (invalidFiles.isEmpty()) {
            List<UploadedFileModel> models = files.stream()
                    .map(this::executeFileUpload)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(models);
        }
        Set<InvalidFileTypeConstraintViolation> violations = invalidFiles.stream()
                .map(InvalidFileTypeConstraintViolation::new)
                .collect(Collectors.toSet());
        throw new InvalidFileTypeException("At least one file has invalid type!", violations);
    }

    /**
     * Execute file upload
     *
     * @param file multipart file
     * @return uploaded file model
     */
    private UploadedFileModel executeFileUpload(MultipartFile file) {
        UploadFileCommand command = fileWebMapper.mapToCommand(file);
        File uploadedFile = uploadFileUseCase.upload(command);
        return fileWebMapper.mapToModel(uploadedFile);
    }

    /**
     * Check if any file has wrong type
     *
     * @param files multipart files
     * @return empty list if all provided files has normal type otherwise returns files with wrong types
     */
    private List<MultipartFile> anyHasWrongType(List<MultipartFile> files) {
        return files.stream()
                .filter(this::hasWrongType)
                .collect(Collectors.toList());
    }

    /**
     * Check if file has wrong type
     *
     * @param file multipart file
     * @return true if file has wrong type otherwise false
     */
    private boolean hasWrongType(MultipartFile file) {
        return !ALLOWED_TYPES.contains(file.getContentType());
    }

}

package food.booking.app.storage.adapter.in.web;

import food.booking.app.storage.app.port.in.UploadFileCommand;
import food.booking.app.storage.app.port.in.UploadFileUseCase;
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

import java.util.Arrays;
import java.util.List;
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

    private final static String[] ALLOWED_TYPES = {"image/gif", "image/jpeg", "image/png"};

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
            log.warn("File has wrong type {}", file.getContentType());
            return ResponseEntity.badRequest().build();
        }
        UploadedFileModel dto = executeFileUpload(file);
        return ResponseEntity.created(dto.url()).body(dto);
    }

    /**
     * Upload multiple files
     *
     * @param files multipart files
     * @return list of uploaded file responses
     */
    @PostMapping("/uploadMultipleFiles")
    ResponseEntity<List<UploadedFileModel>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        log.debug("REST request to upload multiple files: {}", (Object[]) files);
        if (anyHasWrongType(files)) {
            List<String> fileTypes = Arrays.stream(files)
                    .map(MultipartFile::getContentType)
                    .collect(Collectors.toList());
            log.warn("File has wrong type: {}", fileTypes);
            return ResponseEntity.badRequest().build();
        }
        List<UploadedFileModel> dtos = Arrays.stream(files).map(this::executeFileUpload).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Execute file upload
     *
     * @param file multipart file
     * @return uploaded file model
     */
    private UploadedFileModel executeFileUpload(MultipartFile file) {
        UploadFileCommand command = fileWebMapper.mapToCommand(file);
        File upload = uploadFileUseCase.upload(command);
        return fileWebMapper.mapToModel(upload);
    }

    /**
     * Check if any has wrong type
     *
     * @param files files
     * @return true if any file has a wrong type otherwise false if all files are valid
     */
    private boolean anyHasWrongType(MultipartFile[] files) {
        for (MultipartFile file : files) {
            if (hasWrongType(file)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if file has a wrong type
     *
     * @param file file
     * @return true if file has a wrong type otherwise false
     */
    private boolean hasWrongType(MultipartFile file) {
        return !Arrays.asList(ALLOWED_TYPES).contains(file.getContentType());
    }

}

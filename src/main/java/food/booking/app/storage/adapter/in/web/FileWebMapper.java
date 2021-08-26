package food.booking.app.storage.adapter.in.web;

import food.booking.app.storage.app.port.in.GenerateFileSlugUseCase;
import food.booking.app.storage.app.port.in.UploadFileCommand;
import food.booking.app.storage.app.port.out.FileDownloadUriBuilderPort;
import food.booking.app.storage.domain.File;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * File web mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class FileWebMapper {

    private final GenerateFileSlugUseCase generateFileSlugUseCase;

    private final FileDownloadUriBuilderPort fileDownloadUriBuilderPort;

    /**
     * Map to command
     *
     * @param file multipart file
     * @return upload file command
     */
    UploadFileCommand mapToCommand(MultipartFile file) {
        String fileSlug = generateFileSlugUseCase.generateSlug();
        return new UploadFileCommand(
                file.getResource(),
                fileSlug,
                fileDownloadUriBuilderPort.buildUri(fileSlug));
    }

    /**
     * Map to model
     *
     * @param file file
     * @return uploaded file model
     */
    UploadedFileModel mapToModel(File file) {
        return new UploadedFileModel(
                file.getUrl(),
                file.getSize(),
                file.getSlug(),
                file.getMimeType(),
                file.getChecksum(),
                file.getUploadedAt(),
                file.getOriginalName());
    }

}

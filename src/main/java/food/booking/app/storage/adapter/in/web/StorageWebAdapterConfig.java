package food.booking.app.storage.adapter.in.web;

import food.booking.app.storage.app.port.in.DeleteFileUseCase;
import food.booking.app.storage.app.port.in.GenerateFileSlugUseCase;
import food.booking.app.storage.app.port.in.LoadFileDetailsUseCase;
import food.booking.app.storage.app.port.in.UploadFileUseCase;
import food.booking.app.storage.app.port.out.FileDownloadUriBuilderPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Storage web adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class StorageWebAdapterConfig {

    @Bean
    UploadFileController uploadFileController(FileWebMapper fileWebMapper,
                                              UploadFileUseCase uploadFileUseCase) {
        return new UploadFileController(fileWebMapper, uploadFileUseCase);
    }

    @Bean
    DownloadFileController downloadFileController(LoadFileDetailsUseCase loadFileDetailsUseCase) {
        return new DownloadFileController(loadFileDetailsUseCase);
    }

    @Bean
    DeleteFileController deleteFileController(DeleteFileUseCase deleteFileUseCase) {
        return new DeleteFileController(deleteFileUseCase);
    }

    @Bean
    LoadFileDetailsController loadFileDetailsController(LoadFileDetailsUseCase loadFileDetailsUseCase) {
        return new LoadFileDetailsController(loadFileDetailsUseCase);
    }

    @Bean
    FileDownloadUriBuilderPort fileDownloadUriBuilderPort() {
        return new LocalFileDownloadUriBuilder();
    }

    @Bean
    FileWebMapper fileWebMapper(GenerateFileSlugUseCase generateFileSlugUseCase,
                                FileDownloadUriBuilderPort fileDownloadUriBuilderPort) {
        return new FileWebMapper(generateFileSlugUseCase, fileDownloadUriBuilderPort);
    }

}

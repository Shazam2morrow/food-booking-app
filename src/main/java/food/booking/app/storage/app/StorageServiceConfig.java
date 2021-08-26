package food.booking.app.storage.app;

import food.booking.app.storage.app.port.out.*;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Storage service configuration
 *
 * @author shazam2morrow
 */
@Configuration
class StorageServiceConfig {

    @Bean
    LocalStorageService localStorageService(
            CreateFilePort createFilePort,
            DeleteFilePort deleteFilePort,
            StorageProperties storageProperties,
            CheckFileSlugPort checkFileSlugPort,
            LoadFileDetailsPort loadFileDetailsPort,
            RandomStringGenerator randomStringGenerator) {
        return new LocalStorageService(
                createFilePort,
                deleteFilePort,
                storageProperties,
                checkFileSlugPort,
                loadFileDetailsPort,
                randomStringGenerator);
    }

    @Bean
    FileUriResolver fileUriResolver(CheckFileUrlPort checkFileUrlPort) {
        return new FileUriResolver(checkFileUrlPort);
    }

}

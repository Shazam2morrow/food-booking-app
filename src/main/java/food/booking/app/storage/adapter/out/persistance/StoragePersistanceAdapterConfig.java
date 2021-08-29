package food.booking.app.storage.adapter.out.persistance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Storage persistance adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class StoragePersistanceAdapterConfig {

    @Bean
    StoragePersistanceAdapter storagePersistanceAdapter(
            StoragePersistanceMapper storagePersistanceMapper,
            UploadedFileRepository uploadedFileRepository,
            StoragePersistanceResolver storagePersistanceResolver) {
        return new StoragePersistanceAdapter(
                uploadedFileRepository,
                storagePersistanceMapper,
                storagePersistanceResolver);
    }

    @Bean
    StoragePersistanceResolver storagePersistanceResolver(UploadedFileRepository uploadedFileRepository) {
        return new StoragePersistanceResolver(uploadedFileRepository);
    }

    @Bean
    StoragePersistanceMapper storagePersistanceMapper() {
        return new StoragePersistanceMapper();
    }

}

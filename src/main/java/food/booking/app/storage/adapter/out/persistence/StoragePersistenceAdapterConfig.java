package food.booking.app.storage.adapter.out.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Storage persistence adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class StoragePersistenceAdapterConfig {

    @Bean
    StoragePersistenceAdapter storagepersistenceAdapter(
            StoragePersistenceMapper storagePersistenceMapper,
            UploadedFileRepository uploadedFileRepository,
            StoragePersistenceResolver storagePersistenceResolver) {
        return new StoragePersistenceAdapter(
                uploadedFileRepository,
                storagePersistenceMapper,
                storagePersistenceResolver);
    }

    @Bean
    StoragePersistenceResolver storagepersistenceResolver(UploadedFileRepository uploadedFileRepository) {
        return new StoragePersistenceResolver(uploadedFileRepository);
    }

    @Bean
    StoragePersistenceMapper storagepersistenceMapper() {
        return new StoragePersistenceMapper();
    }

}

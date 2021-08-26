package food.booking.app.storage.app;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * Storage properties
 *
 * @author shazam2morrow
 */
@ConfigurationProperties(prefix = "storage")
class StorageProperties {

    @Getter
    private final String uploadDirectory;

    @ConstructorBinding
    StorageProperties(String uploadDirectory) {
        this.uploadDirectory = uploadDirectory;
    }

}

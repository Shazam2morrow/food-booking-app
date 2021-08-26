package food.booking.app.storage.app.port.in;

import food.booking.app.shared.SelfValidate;
import food.booking.app.shared.validation.Slug;
import food.booking.app.storage.app.port.in.exception.StorageException;
import lombok.Getter;
import org.springframework.core.io.Resource;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URI;
import java.util.HashSet;

/**
 * Upload file command
 *
 * @author shazam2morrow
 */
@Getter
public class UploadFileCommand extends SelfValidate<UploadFileCommand> {

    @Slug
    private final String slug;

    @NotNull(message = "url can not be null")
    private final URI url;

    @NotNull(message = "resource can not be null")
    private final Resource resource;

    public UploadFileCommand(Resource resource, String slug, URI url) {
        this.url = url;
        this.slug = slug;
        this.resource = resource;
        selfValidate();
        requireNotEmpty(resource);
    }

    /**
     * Require not empty resource
     *
     * @param resource resource
     */
    private void requireNotEmpty(Resource resource) {
        try {
            if (resource.contentLength() == 0) {
                throw new ConstraintViolationException("Resource can not be empty!", new HashSet<>());
            }
        } catch (IOException ex) {
            throw new StorageException("Could not retrieve size of a resource. Please try again!", ex);
        }
    }

}

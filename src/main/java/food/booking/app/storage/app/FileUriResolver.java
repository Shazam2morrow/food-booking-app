package food.booking.app.storage.app;

import food.booking.app.storage.app.port.out.CheckFileUrlPort;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nullable;
import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * File URI resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
public class FileUriResolver {

    private final CheckFileUrlPort checkFileUrlPort;

    /**
     * Resolve file URL
     *
     * @param fileUrl file rul
     * @return resolved file url
     */
    @Nullable
    public URI resolve(URI fileUrl) {
        if (Objects.isNull(fileUrl)) {
            return null;
        }
        if (!checkFileUrlPort.checkUrl(fileUrl)) {
            throw new EntityNotFoundException("File was not found on the provided url " + fileUrl);
        }
        return fileUrl;
    }

    /**
     * Resolver file URLs
     *
     * @param fileUrls file urls
     * @return resolved file urls
     */
    public List<URI> resolve(List<URI> fileUrls) {
        if (Objects.isNull(fileUrls) || fileUrls.isEmpty()) {
            return new ArrayList<>();
        }
        for (URI fileUrl : fileUrls) {
            if (!checkFileUrlPort.checkUrl(fileUrl)) {
                throw new EntityNotFoundException("File was not found on the provided url " + fileUrl);
            }
        }
        return fileUrls;
    }

}

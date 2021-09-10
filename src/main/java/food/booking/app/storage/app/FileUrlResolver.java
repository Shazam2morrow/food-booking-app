package food.booking.app.storage.app;

import food.booking.app.storage.app.port.in.exception.FileNotFoundException;
import food.booking.app.storage.app.port.out.CheckFileUrlPort;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nullable;
import java.net.URI;
import java.util.List;
import java.util.Objects;

/**
 * File url resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
public class FileUrlResolver {

    private final CheckFileUrlPort checkFileUrlPort;

    /**
     * Resolve file url
     *
     * @param fileUrl file url
     * @return resolved file url
     * @throws FileNotFoundException if file was not found on the provided url
     */
    @Nullable
    public URI resolve(@Nullable URI fileUrl) {
        if (Objects.nonNull(fileUrl) && !checkFileUrlPort.checkUrl(fileUrl)) {
            throw new FileNotFoundException(fileUrl);
        }
        return fileUrl;
    }

    /**
     * Resolve file urls
     *
     * @param fileUrls file urls
     * @return resolved file urls
     * @throws FileNotFoundException if file was not found on the provided url
     */
    @Nullable
    public List<URI> resolve(@Nullable List<URI> fileUrls) {
        if (Objects.nonNull(fileUrls)) {
            for (URI fileUrl : fileUrls) {
                if (!checkFileUrlPort.checkUrl(fileUrl)) {
                    throw new FileNotFoundException(fileUrl);
                }
            }
        }
        return fileUrls;
    }

}

package food.booking.app.storage.app.port.out;

import java.net.URI;

/**
 * File download URI builder port
 *
 * @author shazam2morrow
 */
public interface FileDownloadUriBuilderPort {

    /**
     * Build file download URI
     *
     * @param fileSlug file slug
     * @return file download URI
     */
    URI buildUri(String fileSlug);

}

package food.booking.app.storage.app.port.out;

import java.net.URI;

/**
 * Check file URL port
 *
 * @author shazam2morrow
 */
public interface CheckFileUrlPort {

    /**
     * Check if file url is valid
     *
     * @param fileUrl file url
     * @return true if file URL is valid otherwise false
     */
    boolean checkUrl(URI fileUrl);

}

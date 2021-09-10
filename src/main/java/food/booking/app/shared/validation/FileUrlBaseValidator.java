package food.booking.app.shared.validation;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

/**
 * File url base validator
 *
 * @author shazam2morrow
 */
public abstract class FileUrlBaseValidator {

    /**
     * Is file url valid?
     *
     * @param fileUrl file url
     * @return true if file url is valid otherwise false
     */
    protected boolean isValid(URI fileUrl) {
        try {
            return fileUrl.toURL().getProtocol().startsWith("http");
        } catch (MalformedURLException | IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Are file urls valid?
     *
     * @param fileUrls filr urls
     * @return true if file urls are valid otherwise false if any is not
     */
    protected boolean isValid(List<URI> fileUrls) {
        for (URI fileUrl : fileUrls) {
            if (!isValid(fileUrl)) {
                return false;
            }
        }
        return true;
    }

}

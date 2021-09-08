package food.booking.app.storage.app.port.in.exception;

import food.booking.app.shared.exception.NoSuchElementException;

/**
 * Thrown when file was not found on the provided URL
 *
 * @author shazam2morrow
 */
public class FileUrlNotFoundException extends NoSuchElementException {

    private static final String CODE = "file.url.notfound";

    public FileUrlNotFoundException(Object id) {
        super(id, "File was not found on the provided url " + id);
    }

    @Override
    public String getMessageCode() {
        return CODE;
    }

}

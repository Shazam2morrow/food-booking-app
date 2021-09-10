package food.booking.app.storage.app.port.in.exception;

import food.booking.app.shared.exception.NoSuchElementException;

/**
 * Thrown when file was not found
 *
 * @author shazam2morrow
 */
public class FileNotFoundException extends NoSuchElementException {

    private static final String CODE = "file.notfound";

    public FileNotFoundException(Object id) {
        super(id);
    }

    @Override
    public String getMessageCode() {
        return CODE;
    }

}

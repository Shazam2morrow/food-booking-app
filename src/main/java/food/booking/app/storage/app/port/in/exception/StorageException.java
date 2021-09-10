package food.booking.app.storage.app.port.in.exception;

import food.booking.app.shared.exception.ServiceException;

/**
 * Thrown when file could not be stored
 *
 * @author shazam2morrow
 */
public class StorageException extends ServiceException {

    private final static String CODE = "storage.service.fail";

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessageCode() {
        return CODE;
    }

}

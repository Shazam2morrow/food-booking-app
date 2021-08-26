package food.booking.app.storage.app.port.in.exception;

/**
 * Thrown when error happened during storage operation
 *
 * @author shazam2morrow
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

}

package food.booking.app.storage.app.port.in.exception;

/**
 * Thrown when file path contains invalid characters
 *
 * @author shazam2morrow
 */
public class InvalidPathException extends RuntimeException {

    public InvalidPathException(String mesage) {
        super(mesage);
    }

}

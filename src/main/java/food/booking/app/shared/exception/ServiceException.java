package food.booking.app.shared.exception;

/**
 * Thrown when service error happened
 *
 * @author shazam2morrow
 */
public abstract class ServiceException extends LocalizedException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}

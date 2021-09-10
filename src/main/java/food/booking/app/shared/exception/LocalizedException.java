package food.booking.app.shared.exception;

/**
 * Localized exception
 *
 * @author shazam2morrow
 */
public abstract class LocalizedException extends RuntimeException implements HasMessageCode {

    public LocalizedException() {
        super();
    }

    public LocalizedException(String message) {
        super(message);
    }

    public LocalizedException(String message, Throwable cause) {
        super(message, cause);
    }

}

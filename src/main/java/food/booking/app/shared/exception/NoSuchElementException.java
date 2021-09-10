package food.booking.app.shared.exception;

import lombok.Getter;

/**
 * Thrown when element was not found
 *
 * @author shazam2morrow
 */
public abstract class NoSuchElementException extends LocalizedException {

    @Getter
    private final Object id;

    public NoSuchElementException(Object id) {
        this.id = id;
    }

}

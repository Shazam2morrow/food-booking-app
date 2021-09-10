package food.booking.app.storage.app.port.in.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Thrown when file name contains invalid characters
 *
 * @author shazam2morrow
 */
public class InvalidFileNameException extends ConstraintViolationException {

    public InvalidFileNameException(String message, Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(message, constraintViolations);
    }

    public InvalidFileNameException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }

}

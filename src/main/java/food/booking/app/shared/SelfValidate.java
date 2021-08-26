package food.booking.app.shared;

import javax.validation.*;
import java.util.Set;

/**
 * Self validate class
 *
 * @param <T> type
 * @author shazam2morrow
 */
public abstract class SelfValidate<T> {

    private final Validator validator;

    public SelfValidate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    protected void selfValidate() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}

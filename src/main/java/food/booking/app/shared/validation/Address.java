package food.booking.app.shared.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Address validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddressValidator.class)
public @interface Address {

    String message() default "{Address.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

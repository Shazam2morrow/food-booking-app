package food.booking.app.shared.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

/**
 * Aliases validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Size(min = 3, max = 10, message = "aliases.invalid.size")
public @interface Aliases {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

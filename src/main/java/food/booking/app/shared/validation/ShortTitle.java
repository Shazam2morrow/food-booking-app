package food.booking.app.shared.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Short title validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ShortTitleValidator.class)
public @interface ShortTitle {

    String message() default "{ShortTitle.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

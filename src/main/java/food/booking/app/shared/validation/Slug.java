package food.booking.app.shared.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Slug validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SlugValidator.class)
public @interface Slug {

    String message() default "{Slug.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

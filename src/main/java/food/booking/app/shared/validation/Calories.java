package food.booking.app.shared.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.*;

/**
 * Calories validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@PositiveOrZero(message = "calories.notnegative")
public @interface Calories {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

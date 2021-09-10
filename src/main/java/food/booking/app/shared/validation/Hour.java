package food.booking.app.shared.validation;

import food.booking.app.shared.size.HourSize;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * Hour validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "hour.notnull")
@Min(value = HourSize.MIN, message = "hour.invalid.min")
@Max(value = HourSize.MAX, message = "hour.invalid.max")
public @interface Hour {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

package food.booking.app.shared.validation;

import food.booking.app.shared.size.MinuteSize;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * Minute validation annotation
 *
 * @author shaza2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "minute.notnull")
@Min(value = MinuteSize.MIN, message = "minute.invalid.min")
@Max(value = MinuteSize.MAX, message = "minute.invalid.max")
public @interface Minute {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

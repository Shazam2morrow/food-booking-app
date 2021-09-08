package food.booking.app.shared.validation;

import food.booking.app.shared.size.ScheduleSize;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

/**
 * Schedule validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "schedule.notnull")
@Size(min = ScheduleSize.MAX, max = ScheduleSize.MAX, message = "schedule.invalid.size")
public @interface Schedule {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

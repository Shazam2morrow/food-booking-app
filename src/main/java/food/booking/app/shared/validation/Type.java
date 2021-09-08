package food.booking.app.shared.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * Type validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = {})
@NotNull(message = "type.notnull")
@Retention(RetentionPolicy.RUNTIME)
public @interface Type {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

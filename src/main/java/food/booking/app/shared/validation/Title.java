package food.booking.app.shared.validation;

import food.booking.app.shared.size.TitleSize;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

/**
 * Title validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "title.notnull")
@Size(min = TitleSize.MIN, max = TitleSize.MAX, message = "title.invalid.size")
public @interface Title {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

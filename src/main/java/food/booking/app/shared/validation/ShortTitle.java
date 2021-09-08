package food.booking.app.shared.validation;

import food.booking.app.shared.size.ShortTitleSize;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

/**
 * Short title validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Size(min = ShortTitleSize.MIN, max = ShortTitleSize.MAX, message = "shorttitle.invalid.size")
public @interface ShortTitle {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

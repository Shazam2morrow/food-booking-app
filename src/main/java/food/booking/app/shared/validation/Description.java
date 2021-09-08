package food.booking.app.shared.validation;

import food.booking.app.shared.size.DescriptionSize;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

/**
 * Description validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Size(min = DescriptionSize.MIN, max = DescriptionSize.MAX, message = "description.invalid.size")
public @interface Description {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

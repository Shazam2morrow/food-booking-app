package food.booking.app.shared.validation;

import food.booking.app.shared.size.AliasesSize;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

/**
 * Aliases validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Size(min = AliasesSize.MIN, max = AliasesSize.MAX, message = "aliases.invalid.size")
public @interface Aliases {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

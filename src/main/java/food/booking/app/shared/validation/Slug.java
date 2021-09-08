package food.booking.app.shared.validation;

import food.booking.app.shared.size.SlugSize;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

/**
 * Slug validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "slug.notnull")
@Size(min = SlugSize.MAX, max = SlugSize.MAX, message = "slug.size.invalid")
public @interface Slug {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

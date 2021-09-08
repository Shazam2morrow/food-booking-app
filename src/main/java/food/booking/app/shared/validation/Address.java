package food.booking.app.shared.validation;

import food.booking.app.shared.size.AddressSize;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

/**
 * Address validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "address.notnull")
@Size(min = AddressSize.MIN, max = AddressSize.MAX, message = "address.invalid.size")
public @interface Address {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

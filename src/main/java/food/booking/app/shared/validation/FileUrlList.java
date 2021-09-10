package food.booking.app.shared.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * File url validation annotation
 *
 * @author shazam2morrow
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileUrlListValidator.class)
public @interface FileUrlList {

    String message() default "file.url.invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}


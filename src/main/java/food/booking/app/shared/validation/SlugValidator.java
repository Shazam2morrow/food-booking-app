package food.booking.app.shared.validation;

import food.booking.app.shared.size.SlugSize;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Slug validator
 *
 * @author shazam2morrow
 */
@NoArgsConstructor
public class SlugValidator implements ConstraintValidator<Slug, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isNotBlank(value) && value.length() == SlugSize.MAX;
    }

}

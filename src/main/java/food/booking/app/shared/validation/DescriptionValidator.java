package food.booking.app.shared.validation;

import food.booking.app.shared.size.DescriptionSize;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Description validator
 *
 * @author shazam2morrow
 */
@NoArgsConstructor
public class DescriptionValidator implements ConstraintValidator<Description, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isEmpty(value) || hasValidLength(value);
    }

    private boolean hasValidLength(String value) {
        int length = value.length();
        return (length >= DescriptionSize.MIN) && (length <= DescriptionSize.MAX);
    }

}

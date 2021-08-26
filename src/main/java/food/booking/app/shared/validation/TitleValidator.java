package food.booking.app.shared.validation;

import food.booking.app.shared.size.TitleSize;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Title validator
 *
 * @author shazam2morrow
 */
@NoArgsConstructor
public class TitleValidator implements ConstraintValidator<Title, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isNotBlank(value) && hasValidLength(value);
    }

    private boolean hasValidLength(String value) {
        int length = value.length();
        return (length >= TitleSize.MIN) && (length <= TitleSize.MAX);
    }

}

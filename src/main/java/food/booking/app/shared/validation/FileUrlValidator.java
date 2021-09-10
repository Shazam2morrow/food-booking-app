package food.booking.app.shared.validation;

import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.URI;
import java.util.Objects;

/**
 * File url validator
 *
 * @author shazam2morrow
 */
@NoArgsConstructor
public class FileUrlValidator extends FileUrlBaseValidator
        implements ConstraintValidator<FileUrl, URI> {

    /**
     * Is file url valid?
     *
     * @param fileUrl optional file url
     * @param context context
     * @return true if file url is valid otherwise false
     */
    @Override
    public boolean isValid(@Nullable URI fileUrl, ConstraintValidatorContext context) {
        return Objects.isNull(fileUrl) || isValid(fileUrl);
    }

}

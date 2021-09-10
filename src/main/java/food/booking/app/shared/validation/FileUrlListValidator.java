package food.booking.app.shared.validation;

import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.URI;
import java.util.List;
import java.util.Objects;

/**
 * File url list validator
 *
 * @author shazam2morrow
 */
@NoArgsConstructor
public class FileUrlListValidator extends FileUrlBaseValidator
        implements ConstraintValidator<FileUrlList, List<URI>> {

    /**
     * Are file urls valid?
     *
     * @param fileUrls file urls
     * @param context context
     * @return true if file urls are valid otherwise false if any is not
     */
    @Override
    public boolean isValid(@Nullable List<URI> fileUrls, ConstraintValidatorContext context) {
        return Objects.isNull(fileUrls) || isValid(fileUrls);
    }

}

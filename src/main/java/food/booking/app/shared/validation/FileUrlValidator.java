package food.booking.app.shared.validation;

import lombok.NoArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Objects;

/**
 * File url validator
 *
 * @author shazam2morrow
 */
@NoArgsConstructor
public class FileUrlValidator implements ConstraintValidator<FileUrlOrNull, URI> {

    @Override
    public boolean isValid(URI fileUrl, ConstraintValidatorContext context) {
        if (Objects.isNull(fileUrl)) {
            return true;
        }
        try {
            return fileUrl.toURL().getProtocol().startsWith("http");
        } catch (MalformedURLException | IllegalArgumentException ex) {
            return false;
        }
    }

}

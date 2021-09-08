package food.booking.app.shared.validation;

import lombok.NoArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class FileUrlListValidator implements ConstraintValidator<FileUrlListOrNull, List<URI>> {

    @Override
    public boolean isValid(List<URI> fileUrls, ConstraintValidatorContext context) {
        if (Objects.isNull(fileUrls)) {
            return true;
        }
        for (URI fileUrl : fileUrls) {
            if (!isValid(fileUrl)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(URI fileUrl) {
        try {
            return fileUrl.toURL().getProtocol().startsWith("http");
        } catch (MalformedURLException | IllegalArgumentException ex) {
            return false;
        }
    }

}

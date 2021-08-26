package food.booking.app.shared.persistance;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.net.URI;
import java.util.Objects;

/**
 * Uri persistance converter
 *
 * @author shazam2morrow
 */
@Converter
public class UriPersistanceConverter implements AttributeConverter<URI, String> {

    @Override
    public String convertToDatabaseColumn(URI uri) {
        return Objects.isNull(uri) ? null : uri.toString();
    }

    @Override
    public URI convertToEntityAttribute(String value) {
        return StringUtils.isBlank(value) ? null : URI.create(value.trim());
    }

}

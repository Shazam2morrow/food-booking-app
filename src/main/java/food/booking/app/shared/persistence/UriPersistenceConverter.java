package food.booking.app.shared.persistence;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.net.URI;
import java.util.Objects;

/**
 * Uri persistence converter
 *
 * @author shazam2morrow
 */
@Converter
public class UriPersistenceConverter implements AttributeConverter<URI, String> {

    @Override
    public String convertToDatabaseColumn(URI uri) {
        return Objects.isNull(uri) ? null : uri.toString();
    }

    @Override
    public URI convertToEntityAttribute(String value) {
        return StringUtils.isBlank(value) ? null : URI.create(value.trim());
    }

}

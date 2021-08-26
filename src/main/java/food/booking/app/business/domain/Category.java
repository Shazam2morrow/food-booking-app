package food.booking.app.business.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.net.URI;
import java.time.Instant;

/**
 * Category domain entity
 *
 * @author shazam2morrow
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {

    private URI iconUrl;
    private String slug;
    private String title;
    private Boolean active;
    private Short sortOrder;
    private Instant createdAt;
    private Instant updatedAt;

    public Boolean isActive() {
        return active;
    }

}

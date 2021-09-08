package food.booking.app.business.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import food.booking.app.shared.Slugable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Menu domain entity
 *
 * @author shazam2morrow
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu implements Slugable<String> {

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

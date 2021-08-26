package food.booking.app.business.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;

/**
 * Item domain entity
 *
 * @author shazam2morrow
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

    private String slug;
    private String title;
    private URI bannerUrl;
    private Boolean active;
    private Integer calories;
    private BigDecimal price;
    private Instant createdAt;
    private Instant updatedAt;
    private Integer sortOrder;
    private Short cookingTime;
    private String description;

    public Boolean isActive() {
        return active;
    }

}

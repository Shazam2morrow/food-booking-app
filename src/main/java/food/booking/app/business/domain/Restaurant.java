package food.booking.app.business.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import food.booking.app.business.adapter.out.persistence.RestaurantType;
import food.booking.app.shared.Slugable;
import food.booking.app.shared.domain.DailySchedule;
import food.booking.app.shared.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * Restaurant domain entity
 *
 * @author shazam2morrow
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Restaurant implements Slugable<String> {

    private String slug;
    private String title;
    private Short rating;
    private URI bannerUrl;
    private Boolean active;
    private String address;
    private List<URI> media;
    private Instant createdAt;
    private Instant updatedAt;
    private Location location;
    private String shortTitle;
    private String description;
    private RestaurantType type;
    private List<String> aliases;
    private List<Category> categories;
    private BigDecimal averageReceipt;
    private List<DailySchedule> schedule;

    public Boolean isActive() {
        return active;
    }

    public boolean hasCategories() {
        return Objects.nonNull(categories) && !categories.isEmpty();
    }

    public String getSlug() {
        return slug;
    }

}

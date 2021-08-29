package food.booking.app.business.app.port.in.restaurant;

import com.fasterxml.jackson.annotation.JsonInclude;
import food.booking.app.business.adapter.out.persistence.RestaurantType;
import food.booking.app.business.domain.Category;
import food.booking.app.shared.domain.DailySchedule;
import food.booking.app.shared.domain.Location;
import lombok.Data;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.List;

/**
 * Create restaurant DTO
 *
 * @author shazam2morrow
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatedRestaurantDto {

    private String slug;
    private String title;
    private URI bannerUrl;
    private String address;
    private Boolean active;
    private List<URI> images;
    private Instant createdAt;
    private String shortTitle;
    private Location location;
    private String description;
    private RestaurantType type;
    private List<String> aliases;
    private BigDecimal averageReceipt;
    private List<Category> categories;
    private List<DailySchedule> schedule;

}

package food.booking.app.business.app.port.in.restaurant;

import com.fasterxml.jackson.annotation.JsonInclude;
import food.booking.app.business.adapter.out.persistence.RestaurantType;
import food.booking.app.business.domain.Category;
import food.booking.app.shared.domain.DailySchedule;
import food.booking.app.shared.domain.Location;
import lombok.Data;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

/**
 * Restaurant details DTO
 *
 * @author shazam2morrow
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantDetailsDto {

    private String slug;
    private String title;
    private URI bannerUrl;
    private Boolean active;
    private String address;
    private List<URI> images;
    private Location location;
    private String shortTitle;
    private String description;
    private RestaurantType type;
    private List<String> aliases;
    private BigDecimal averageReceipt;
    private List<Category> categories;
    private List<DailySchedule> schedule;

}

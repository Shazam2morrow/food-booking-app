package food.booking.app.business.adapter.in.web.restaurant;

import com.fasterxml.jackson.annotation.JsonInclude;
import food.booking.app.business.adapter.out.persistance.RestaurantType;
import food.booking.app.shared.domain.DailySchedule;
import food.booking.app.shared.domain.Location;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

/**
 * Restaurant model
 *
 * @author shazam2morrow
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "restaurant", collectionRelation = "restaurants")
public class RestaurantModel extends RepresentationModel<RestaurantModel> {

    private String slug;
    private String title;
    private URI bannerUrl;
    private Boolean active;
    private String address;
    private List<URI> media;
    private Location location;
    private String shortTitle;
    private String description;
    private RestaurantType type;
    private List<String> aliases;
    private BigDecimal averageReceipt;
    private List<DailySchedule> schedule;

}

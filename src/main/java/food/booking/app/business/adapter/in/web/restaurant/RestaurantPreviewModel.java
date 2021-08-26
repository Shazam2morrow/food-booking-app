package food.booking.app.business.adapter.in.web.restaurant;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

/**
 * Restaurant preview model
 *
 * @author shazam2morrow
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "restaurantPreview", collectionRelation = "restaurantPreviews")
public class RestaurantPreviewModel extends RepresentationModel<RestaurantPreviewModel> {

    private String slug;
    private String title;
    private Short rating;
    private URI bannerUrl;
    private Boolean active;
    private String shortTitle;
    private List<String> aliases;
    private BigDecimal averageReceipt;

    public Boolean isActive() {
        return active;
    }

}

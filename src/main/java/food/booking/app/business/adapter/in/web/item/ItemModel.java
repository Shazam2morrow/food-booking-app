package food.booking.app.business.adapter.in.web.item;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.net.URI;

/**
 * Item model
 *
 * @author shazam2morrow
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "item", collectionRelation = "items")
public class ItemModel extends RepresentationModel<ItemModel> {

    private String slug;
    private String title;
    private URI bannerUrl;
    private Boolean active;
    private Integer calories;
    private BigDecimal price;
    private Integer sortOrder;
    private Short cookingTime;
    private String description;

    public Boolean isActive() {
        return active;
    }

}

package food.booking.app.business.adapter.in.web.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.net.URI;

/**
 * Category model
 *
 * @author shazam2morrow
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "category", collectionRelation = "categories")
public class CategoryModel extends RepresentationModel<CategoryModel> {

    private URI iconUrl;
    private String slug;
    private String title;
    private Boolean active;
    private Short sortOrder;

    public Boolean isActive() {
        return active;
    }

}

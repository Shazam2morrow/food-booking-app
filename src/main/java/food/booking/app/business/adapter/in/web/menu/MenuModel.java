package food.booking.app.business.adapter.in.web.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * Menu model
 *
 * @author shazam2morrow
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "menu", collectionRelation = "menus")
public class MenuModel extends RepresentationModel<MenuModel> {

    private String slug;
    private String title;
    private Boolean active;
    private Short sortOrder;

    public Boolean isActive() {
        return active;
    }

}

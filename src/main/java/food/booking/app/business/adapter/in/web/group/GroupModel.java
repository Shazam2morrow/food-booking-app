package food.booking.app.business.adapter.in.web.group;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.net.URI;

/**
 * Group model
 *
 * @author shazam2morrow
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "group", collectionRelation = "groups")
public class GroupModel extends RepresentationModel<GroupModel> {

    private URI iconUrl;
    private String slug;
    private String title;
    private Boolean active;
    private Short sortOrder;

    public Boolean isActive() {
        return active;
    }

}

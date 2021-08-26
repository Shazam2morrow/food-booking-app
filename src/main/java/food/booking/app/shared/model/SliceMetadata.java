package food.booking.app.shared.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;

/**
 * Slice metadata
 *
 * @author shazam2morrow
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "slice", collectionRelation = "slices")
public class SliceMetadata {

    private Integer page;
    private Integer size;
    private Boolean last;
    private Boolean first;
    private Boolean hasNext;
    private Boolean hasContent;
    private Boolean hasPrevious;
    private Integer numberOfElements;

}

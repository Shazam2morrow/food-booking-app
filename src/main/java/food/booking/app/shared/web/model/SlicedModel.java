package food.booking.app.shared.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Sliced model
 *
 * @param <T> type
 * @author shazam2morrow
 */
public class SlicedModel<T> extends CollectionModel<T> {

    public static final String SELF = "self";

    public static final String NEXT = "next";

    public static final String PREVIOUS = "previous";

    @Getter
    @Nullable
    @JsonProperty("slice")
    private final SliceMetadata sliceMetadata;

    public SlicedModel(Iterable<T> content, @Nullable SliceMetadata sliceMetadata, List<Link> links) {
        super(content, links);
        this.sliceMetadata = sliceMetadata;
    }

}

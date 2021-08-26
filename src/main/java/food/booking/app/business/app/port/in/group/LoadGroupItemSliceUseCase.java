package food.booking.app.business.app.port.in.group;

import food.booking.app.business.domain.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

/**
 * Load group item slice use case
 *
 * @author shazam2morrow
 */
public interface LoadGroupItemSliceUseCase {

    /**
     * Load slice of group items
     *
     * @param groupSlug group slug
     * @param page      page
     * @return slice of group items
     */
    Slice<Item> loadSliceByGroupSlug(String groupSlug, Pageable page);

}

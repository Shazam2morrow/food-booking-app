package food.booking.app.business.app.port.out.group;

import food.booking.app.business.domain.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

/**
 * Load group item slice port
 *
 * @author shazam2morrow
 */
public interface LoadGroupItemSlicePort {

    /**
     * Load slice of group items
     *
     * @param groupSlug group slug
     * @param page      page
     * @return slice of group items
     */
    Slice<Item> loadSliceByGroupSlug(String groupSlug, Pageable page);

}

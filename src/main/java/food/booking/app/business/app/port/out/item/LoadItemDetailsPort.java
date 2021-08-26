package food.booking.app.business.app.port.out.item;

import food.booking.app.business.domain.Item;

/**
 * Load item details port
 *
 * @author shazam2morrow
 */
public interface LoadItemDetailsPort {

    /**
     * Load item details
     *
     * @param itemSlug item slug
     * @return item details
     */
    Item loadDetailsBySlug(String itemSlug);

}

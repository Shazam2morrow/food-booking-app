package food.booking.app.business.app.port.in.item;

import food.booking.app.business.domain.Item;

/**
 * Load item details use case
 *
 * @author shazam2morrow
 */
public interface LoadItemDetailsUseCase {

    /**
     * Load item details
     *
     * @param itemSlug item slug
     * @return item details
     */
    Item loadDetailsBySlug(String itemSlug);

}

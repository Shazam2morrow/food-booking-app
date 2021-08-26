package food.booking.app.business.app.port.out.item;

import food.booking.app.business.domain.Item;

/**
 * Create item port
 *
 * @author shazam2morrow
 */
public interface CreateItemPort {

    /**
     * Create item
     *
     * @param createItem create item
     * @return item
     */
    Item create(CreateItem createItem);

}

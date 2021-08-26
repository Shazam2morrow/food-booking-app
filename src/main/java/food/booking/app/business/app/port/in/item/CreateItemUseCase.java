package food.booking.app.business.app.port.in.item;

import food.booking.app.business.domain.Item;

/**
 * Create item use case
 *
 * @author shazam2morrow
 */
public interface CreateItemUseCase {

    /**
     * Create item
     *
     * @param command create item command
     * @return item
     */
    Item create(CreateItemCommand command);

}

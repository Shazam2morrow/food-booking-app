package food.booking.app.business.app.port.in.item;

import javax.validation.Valid;

/**
 * Update item details use case
 *
 * @author shazam2morrow
 */
public interface UpdateItemDetailsUseCase {

    /**
     * Update item details
     *
     * @param command update item details command
     */
    void update(@Valid UpdateItemDetailsCommand command);

}

package food.booking.app.business.app.port.in.menu;

import javax.validation.Valid;

/**
 * Update menu details use case
 *
 * @author shazam2morrow
 */
public interface UpdateMenuDetailsUseCase {

    /**
     * Update menu details
     *
     * @param command update menu details command
     */
    void update(@Valid UpdateMenuDetailsCommand command);

}

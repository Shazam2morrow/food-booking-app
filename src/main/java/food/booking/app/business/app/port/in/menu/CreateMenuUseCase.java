package food.booking.app.business.app.port.in.menu;

import food.booking.app.business.domain.Menu;

/**
 * Create menu use case
 *
 * @author shazam2morrow
 */
public interface CreateMenuUseCase {

    /**
     * Create menu
     *
     * @param command create menu command
     * @return create menu DTO
     */
    Menu create(CreateMenuCommand command);

}

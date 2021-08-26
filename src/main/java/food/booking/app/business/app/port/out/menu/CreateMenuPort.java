package food.booking.app.business.app.port.out.menu;

import food.booking.app.business.domain.Menu;

/**
 * Create menu port
 *
 * @author shazam2morrow
 */
public interface CreateMenuPort {

    /**
     * Create menu
     *
     * @param createMenu create menu
     * @return menu
     */
    Menu create(CreateMenu createMenu);

}

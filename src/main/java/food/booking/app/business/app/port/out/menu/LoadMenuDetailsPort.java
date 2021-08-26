package food.booking.app.business.app.port.out.menu;

import food.booking.app.business.domain.Menu;

/**
 * Load menu details port
 *
 * @author shazam2morrow
 */
public interface LoadMenuDetailsPort {

    /**
     * Load menu details
     *
     * @param menuSlug menu slug
     * @return menu
     */
    Menu loadDetailsBySlug(String menuSlug);

}

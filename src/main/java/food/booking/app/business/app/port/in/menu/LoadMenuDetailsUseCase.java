package food.booking.app.business.app.port.in.menu;

import food.booking.app.business.domain.Menu;

/**
 * Load menu details use case
 *
 * @author shazam2morrow
 */
public interface LoadMenuDetailsUseCase {

    /**
     * Load menu details
     *
     * @param menuSlug menu slug
     * @return menu details
     */
    Menu loadDetailsBySlug(String menuSlug);

}

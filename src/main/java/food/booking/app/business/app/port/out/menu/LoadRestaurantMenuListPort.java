package food.booking.app.business.app.port.out.menu;

import food.booking.app.business.domain.Menu;

import java.util.List;

/**
 * Load restaurant menu list port
 *
 * @author shazam2morrow
 */
public interface LoadRestaurantMenuListPort {

    /**
     * Load restaurant menu list
     *
     * @param restaurantSlug restaurant slug
     * @return list of restaurant menus
     */
    List<Menu> loadRestaurantMenuList(String restaurantSlug);

}

package food.booking.app.business.app.port.in.menu;

import food.booking.app.business.domain.Menu;

import java.util.List;

/**
 * Load restaurant menu list use case
 *
 * @author shazam2morrow
 */
public interface LoadRestaurantMenuListUseCase {

    /**
     * Load restaurant menu list
     *
     * @param restaurantSlug restaurant slug
     * @return list of restaurant menus
     */
    List<Menu> loadRestaurantMenuList(String restaurantSlug);

}

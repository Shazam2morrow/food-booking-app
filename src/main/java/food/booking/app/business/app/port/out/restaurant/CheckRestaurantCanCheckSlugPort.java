package food.booking.app.business.app.port.out.restaurant;

import food.booking.app.shared.CanCheckSlug;

/**
 * Check restaurant slug port
 *
 * @author shazam2morrow
 */
public interface CheckRestaurantCanCheckSlugPort extends CanCheckSlug {

    /**
     * Check if restaurant with the given slug exists
     *
     * @param restaurantSlug restaurant slug
     * @return true if restaurant exists or false otherwise
     */
    default boolean checkBySlug(String restaurantSlug) {
        return check(restaurantSlug);
    }

}

package food.booking.app.business.app.port.out.restaurant;

import food.booking.app.shared.SlugCheckable;

/**
 * Check restaurant slug port
 *
 * @author shazam2morrow
 */
public interface CheckRestaurantSlugPort extends SlugCheckable {

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

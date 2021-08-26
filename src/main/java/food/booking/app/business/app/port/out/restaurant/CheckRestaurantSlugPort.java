package food.booking.app.business.app.port.out.restaurant;

/**
 * Check restaurant slug port
 *
 * @author shazam2morrow
 */
public interface CheckRestaurantSlugPort {

    /**
     * Check if restaurant with the given slug exists
     *
     * @param restaurantSlug restaurant slug
     * @return true if restaurant exists or false otherwise
     */
    boolean checkBySlug(String restaurantSlug);

}

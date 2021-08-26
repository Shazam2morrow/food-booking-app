package food.booking.app.business.app.port.out.restaurant;

import food.booking.app.business.domain.Restaurant;

/**
 * Load restaurant details port
 *
 * @author shazam2morrow
 */
public interface LoadRestaurantDetailsPort {

    /**
     * Load restaurant details
     *
     * @param restaurantSlug restaurant slug
     * @return restaurant details
     */
    Restaurant loadDetailsBySlug(String restaurantSlug);

}

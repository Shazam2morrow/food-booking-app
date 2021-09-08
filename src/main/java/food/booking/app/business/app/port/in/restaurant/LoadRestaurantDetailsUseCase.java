package food.booking.app.business.app.port.in.restaurant;

import food.booking.app.business.app.port.in.restaurant.exception.RestaurantNotFoundException;
import food.booking.app.business.domain.Restaurant;

/**
 * Load restaurant details use case
 *
 * @author shazam2morrow
 */
public interface LoadRestaurantDetailsUseCase {

    /**
     * Load restaurant details
     *
     * @param restaurantSlug restaurant slug
     * @return restaurant details
     * @throws RestaurantNotFoundException if restaurant was not found
     */
    Restaurant loadDetailsBySlug(String restaurantSlug);

}

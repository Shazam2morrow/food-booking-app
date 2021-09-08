package food.booking.app.business.app.port.in.restaurant;

import food.booking.app.business.app.port.in.restaurant.exception.RestaurantNotFoundException;
import food.booking.app.business.app.port.in.restaurant.exception.RestaurantServiceException;

/**
 * Delete restaurant use case
 *
 * @author shazam2morrow
 */
public interface DeleteRestaurantUseCase {

    /**
     * Delete restaurant
     *
     * @param restaurantSlug restaurant slug
     * @throws RestaurantNotFoundException if restaurant was not found
     * @throws RestaurantServiceException  if restaurant could not be deleted
     */
    void deleteBySlug(String restaurantSlug);

}

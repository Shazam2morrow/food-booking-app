package food.booking.app.business.app.port.in.restaurant;

import food.booking.app.business.app.port.in.restaurant.exception.RestaurantServiceException;
import food.booking.app.business.domain.Restaurant;

import javax.validation.Valid;

/**
 * Create restaurant use case
 *
 * @author shazam2morrow
 */
public interface CreateRestaurantUseCase {

    /**
     * Create restaurant
     *
     * @param command create restaurant command
     * @return created restaurant
     * @throws RestaurantServiceException if restaurant could not be created
     */
    Restaurant create(@Valid CreateRestaurantCommand command);

}

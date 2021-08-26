package food.booking.app.business.app.port.in.restaurant;

import food.booking.app.business.domain.Restaurant;

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
     * @return restaurant
     */
    Restaurant create(CreateRestaurantCommand command);

}

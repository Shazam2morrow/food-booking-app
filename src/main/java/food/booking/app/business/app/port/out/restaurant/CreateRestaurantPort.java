package food.booking.app.business.app.port.out.restaurant;

import food.booking.app.business.domain.Restaurant;

/**
 * Create restaurant port
 *
 * @author shazam2morrow
 */
public interface CreateRestaurantPort {

    /**
     * Create restaurant
     *
     * @param createRestaurant create restaurant
     * @return restaurant
     */
    Restaurant create(CreateRestaurant createRestaurant);

}

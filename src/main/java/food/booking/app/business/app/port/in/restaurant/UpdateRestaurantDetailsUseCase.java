package food.booking.app.business.app.port.in.restaurant;

import food.booking.app.business.app.port.in.restaurant.exception.RestaurantServiceException;

import javax.validation.Valid;

/**
 * Update restaurant details use case
 *
 * @author shazam2morrow
 */
public interface UpdateRestaurantDetailsUseCase {

    /**
     * Update restaurant details
     *
     * @param command update restaurant details command
     * @throws RestaurantServiceException if restaurant details could not be updated
     */
    void update(@Valid UpdateRestaurantDetailsCommand command);

}

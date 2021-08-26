package food.booking.app.business.adapter.out.persistance;

import food.booking.app.business.app.port.out.restaurant.UpdateRestaurantDetails;
import food.booking.app.business.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;

import javax.persistence.EntityNotFoundException;

/**
 * Restaurant persistance resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class RestaurantPersistanceResolver {

    private final RestaurantRepository restaurantRepository;

    RestaurantJpaEntity resolve(Restaurant restaurant) {
        return resolve(requireValid(restaurant).getSlug());
    }

    RestaurantJpaEntity resolve(UpdateRestaurantDetails details) {
        return resolve(requireValid(details).slug());
    }

    RestaurantJpaEntity resolve(String restaurantSlug) {
        return restaurantRepository.findBySlug(requireValidSlug(restaurantSlug))
                .orElseThrow(() -> new EntityNotFoundException("Restaurant was not found " + restaurantSlug));
    }

    /**
     * Validate object
     *
     * @param restaurant restaurant
     * @return validate object
     */
    private Restaurant requireValid(Restaurant restaurant) {
        return Validate.notNull(restaurant, "restaurant can not be null");
    }

    /**
     * Validate object
     *
     * @param details update restaurant details
     * @return validate object
     */
    private UpdateRestaurantDetails requireValid(UpdateRestaurantDetails details) {
        return Validate.notNull(details, "updateRestaurantDetails can not be null");
    }

    /**
     * Validate restaurant slug
     *
     * @param restaurantSlug restaurant slug
     * @return validated restaurant slug
     */
    private String requireValidSlug(String restaurantSlug) {
        return Validate.notBlank(restaurantSlug, "restaurantSlug can not be blank");
    }

}

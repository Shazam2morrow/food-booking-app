package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.in.restaurant.exception.RestaurantNotFoundException;
import food.booking.app.shared.HasSlug;
import lombok.RequiredArgsConstructor;

/**
 * Restaurant persistence resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class RestaurantPersistenceResolver {

    private final RestaurantRepository restaurantRepository;

    /**
     * Resolve restaurant jpa entity
     *
     * @param hasSlug slugable
     * @return resolved restaurant jpa entity
     * @throws RestaurantNotFoundException restaurant was not found
     */
    RestaurantJpaEntity resolve(HasSlug<String> hasSlug) {
        return resolve(hasSlug.getSlug());
    }

    /**
     * Resolve restaurant jpa entity
     *
     * @param restaurantSlug restaurant slug
     * @return resolved restaurant jpa entity
     * @throws RestaurantNotFoundException if restaurant was not found
     */
    RestaurantJpaEntity resolve(String restaurantSlug) {
        return restaurantRepository.findBySlug(restaurantSlug)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantSlug));
    }

}

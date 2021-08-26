package food.booking.app.business.app.port.in.restaurant;

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
     */
    void deleteBySlug(String restaurantSlug);

}

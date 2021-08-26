package food.booking.app.business.app.port.in.restaurant;

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
     */
    void update(UpdateRestaurantDetailsCommand command);

}

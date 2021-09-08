package food.booking.app.business.app.port.in.restaurant.exception;

import food.booking.app.shared.exception.NoSuchElementException;

/**
 * Thrown if restaurant was not found
 *
 * @author shazam2morrow
 */
public class RestaurantNotFoundException extends NoSuchElementException {

    private final static String CODE = "restaurant.notfound";

    public RestaurantNotFoundException(String restaurantSlug) {
        super(restaurantSlug);
    }

    @Override
    public String getMessageCode() {
        return CODE;
    }

}

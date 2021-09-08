package food.booking.app.business.app.port.in.restaurant.exception;

import food.booking.app.shared.exception.ServiceException;

/**
 * Thrown if error happened in restaurant service
 *
 * @author shazam2morrow
 */
public class RestaurantServiceException extends ServiceException {

    private final static String CODE = "restaurant.error";

    public RestaurantServiceException(String message) {
        super(message);
    }

    public RestaurantServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessageCode() {
        return CODE;
    }

}

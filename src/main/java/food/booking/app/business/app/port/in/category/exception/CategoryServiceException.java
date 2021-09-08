package food.booking.app.business.app.port.in.category.exception;

import food.booking.app.shared.exception.ServiceException;

/**
 * Thrown if error happened in category service
 *
 * @author shazam2morrow
 */
public class CategoryServiceException extends ServiceException {

    private final static String CODE = "category.service.fail";

    public CategoryServiceException(String message) {
        super(message);
    }

    public CategoryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessageCode() {
        return CODE;
    }

}

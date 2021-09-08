package food.booking.app.business.app.port.in.item.exception;

import food.booking.app.shared.exception.ServiceException;

/**
 * Thrown when error happened in item service
 *
 * @author shazam2morrow
 */
public class ItemServiceException extends ServiceException {

    private final static String CODE = "item.error";

    public ItemServiceException(String message) {
        super(message);
    }

    public ItemServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessageCode() {
        return CODE;
    }

}

package food.booking.app.business.app.port.in.menu.exception;

import food.booking.app.shared.exception.ServiceException;

/**
 * Thrown if error happened in menu service
 *
 * @author shazam2morrow
 */
public class MenuServiceException extends ServiceException {

    private final static String CODE = "menu.error";

    public MenuServiceException(String message) {
        super(message);
    }

    public MenuServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessageCode() {
        return CODE;
    }

}

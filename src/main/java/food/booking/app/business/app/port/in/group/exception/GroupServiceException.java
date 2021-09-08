package food.booking.app.business.app.port.in.group.exception;

import food.booking.app.shared.exception.ServiceException;

/**
 * Thrown if error happened in group service
 *
 * @author shazam2morrow
 */
public class GroupServiceException extends ServiceException {

    private final static String CODE = "group.error";

    public GroupServiceException(String message) {
        super(message);
    }

    public GroupServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessageCode() {
        return CODE;
    }

}

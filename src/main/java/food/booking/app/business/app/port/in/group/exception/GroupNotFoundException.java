package food.booking.app.business.app.port.in.group.exception;

import food.booking.app.shared.exception.NoSuchElementException;

/**
 * Thrown when group was not found
 *
 * @author shazam2morrow
 */
public class GroupNotFoundException extends NoSuchElementException {

    private final static String CODE = "group.notfound";

    public GroupNotFoundException(String groupSlug) {
        super(groupSlug);
    }

    @Override
    public String getMessageCode() {
        return CODE;
    }

}

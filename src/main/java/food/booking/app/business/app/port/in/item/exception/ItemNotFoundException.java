package food.booking.app.business.app.port.in.item.exception;

import food.booking.app.shared.exception.NoSuchElementException;

/**
 * Thrown when item was not found
 *
 * @author shazam2morrow
 */
public class ItemNotFoundException extends NoSuchElementException {

    private final static String CODE = "item.notfound";

    public ItemNotFoundException(Object id) {
        super(id);
    }

    @Override
    public String getMessageCode() {
        return CODE;
    }

}

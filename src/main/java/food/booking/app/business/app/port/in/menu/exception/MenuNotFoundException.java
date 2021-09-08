package food.booking.app.business.app.port.in.menu.exception;

import food.booking.app.shared.exception.NoSuchElementException;

/**
 * Thrown when menu was not found
 *
 * @author shazam2morrow
 */
public class MenuNotFoundException extends NoSuchElementException {

    private final static String CODE = "menu.notfound";

    public MenuNotFoundException(Object id) {
        super(id);
    }

    @Override
    public String getMessageCode() {
        return CODE;
    }

}

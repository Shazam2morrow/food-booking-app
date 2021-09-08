package food.booking.app.business.app.port.in.category.exception;

import food.booking.app.shared.exception.NoSuchElementException;

/**
 * Thrown when category was not found
 *
 * @author shazam2morrow
 */
public class CategoryNotFoundException extends NoSuchElementException {

    private final static String CODE = "category.notfound";

    public CategoryNotFoundException(String categorySlug) {
        super(categorySlug);
    }

    @Override
    public String getMessageCode() {
        return CODE;
    }

}

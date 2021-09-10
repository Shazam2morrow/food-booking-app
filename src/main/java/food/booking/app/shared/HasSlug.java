package food.booking.app.shared;

/**
 * Has slug contract
 *
 * @author shazam2morrow
 */
public interface HasSlug<T> {

    /**
     * Get object slug
     *
     * @return slug
     */
    T getSlug();

}

package food.booking.app.shared;

/**
 * Slugable contract
 *
 * @author shazam2morrow
 */
public interface Slugable<T> {

    /**
     * Get object slug
     *
     * @return slug
     */
    T getSlug();

}

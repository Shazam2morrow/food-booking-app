package food.booking.app.shared;

/**
 * Can check object slug contract
 *
 * @author shazam2morrow
 */
public interface CanCheckSlug {

    /**
     * Check if object with the given slug already exists
     *
     * @param slug slug
     * @return true if object already exists or false otherwise
     */
    boolean check(String slug);

}

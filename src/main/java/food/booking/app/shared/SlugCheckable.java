package food.booking.app.shared;

/**
 * Check object slug contract
 *
 * @author shazam2morrow
 */
public interface SlugCheckable {

    /**
     * Check if object with the given slug already exists
     *
     * @param slug slug
     * @return true if object already exists or false otherwise
     */
    boolean check(String slug);

}

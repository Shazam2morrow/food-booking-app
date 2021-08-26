package food.booking.app.storage.app.port.out;

/**
 * Check file slug port
 *
 * @author shazam2morrow
 */
public interface CheckFileSlugPort {

    /**
     * Check if file with the given slug already exists
     *
     * @param fileSlug file slug
     * @return true if file exists otherwise false
     */
    boolean checkBySlug(String fileSlug);

}

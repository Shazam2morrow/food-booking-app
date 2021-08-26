package food.booking.app.storage.app.port.out;

/**
 * Delete file port
 *
 * @author shazam2morrow
 */
public interface DeleteFilePort {

    /**
     * Delete file
     *
     * @param fileSlug file slug
     */
    void deleteBySlug(String fileSlug);

}

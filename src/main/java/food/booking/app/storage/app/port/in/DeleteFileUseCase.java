package food.booking.app.storage.app.port.in;

/**
 * Delete file use case
 *
 * @author shazam2morrow
 */
public interface DeleteFileUseCase {

    /**
     * Delete file
     *
     * @param fileSlug file slug
     */
    void deleteBySlug(String fileSlug);

}

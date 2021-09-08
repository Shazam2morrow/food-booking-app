package food.booking.app.storage.app.port.in;

import food.booking.app.storage.app.port.in.exception.FileNotFoundException;

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
     * @throws FileNotFoundException if file was not found
     */
    void deleteBySlug(String fileSlug);

}

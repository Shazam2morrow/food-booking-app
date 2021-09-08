package food.booking.app.storage.app.port.in;

import food.booking.app.storage.app.port.in.exception.FileNotFoundException;
import food.booking.app.storage.domain.File;

/**
 * Load uploaded file details use case
 *
 * @author shazam2morrow
 */
public interface LoadFileDetailsUseCase {

    /**
     * Load file details
     *
     * @param fileSlug file slug
     * @return uploaded file DTO
     * @throws FileNotFoundException if file was not found
     */
    File loadBySlug(String fileSlug);

}

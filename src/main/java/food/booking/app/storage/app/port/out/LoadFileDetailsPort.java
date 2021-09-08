package food.booking.app.storage.app.port.out;

import food.booking.app.storage.app.port.in.exception.FileNotFoundException;
import food.booking.app.storage.domain.File;

/**
 * Load file details port
 *
 * @author shazam2morrow
 */
public interface LoadFileDetailsPort {

    /**
     * Load file details
     *
     * @param fileSlug file slug
     * @return uploaded file DTO
     * @throws FileNotFoundException if file was not found
     */
    File loadBySlug(String fileSlug);

}

package food.booking.app.storage.app.port.out;

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
     */
    File loadBySlug(String fileSlug);

}

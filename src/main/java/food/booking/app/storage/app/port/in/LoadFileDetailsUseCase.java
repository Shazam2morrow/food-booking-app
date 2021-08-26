package food.booking.app.storage.app.port.in;

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
     */
    File loadBySlug(String fileSlug);

}

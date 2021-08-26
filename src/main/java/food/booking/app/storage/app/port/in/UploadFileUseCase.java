package food.booking.app.storage.app.port.in;

import food.booking.app.storage.domain.File;

/**
 * Upload file use case
 *
 * @author shazam2morrow
 */
public interface UploadFileUseCase {

    /**
     * Upload file
     *
     * @param command upload file command
     * @return file
     */
    File upload(UploadFileCommand command);

}

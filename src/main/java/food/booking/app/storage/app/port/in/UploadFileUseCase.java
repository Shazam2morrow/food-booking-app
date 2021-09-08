package food.booking.app.storage.app.port.in;

import food.booking.app.storage.app.port.in.exception.InvalidFileNameException;
import food.booking.app.storage.app.port.in.exception.StorageException;
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
     * @return uploaded file
     * @throws StorageException         if file can not be uploaded
     * @throws InvalidFileNameException if file name contains invalid characters
     */
    File upload(UploadFileCommand command);

}

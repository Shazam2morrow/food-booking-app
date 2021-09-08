package food.booking.app.storage.app.port.in.exception;

import lombok.Getter;

/**
 * Thrown when file could not be stored
 *
 * @author shazam2morrow
 */
public class StorageException extends RuntimeException {

    @Getter
    private final String fileName;

    public final static String CODE = "storage.fail";

    public StorageException(String fileName, Throwable cause) {
        super(cause);
        this.fileName = fileName;
    }

}

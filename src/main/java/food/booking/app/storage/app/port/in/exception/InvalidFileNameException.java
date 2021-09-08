package food.booking.app.storage.app.port.in.exception;

import lombok.Getter;

/**
 * Thrown when file name contains invalid characters
 *
 * @author shazam2morrow
 */
public class InvalidFileNameException extends RuntimeException {

    @Getter
    private final String fileName;

    public static final String CODE = "file.name.invalid";

    public InvalidFileNameException(String fileName) {
        this.fileName = fileName;
    }

}

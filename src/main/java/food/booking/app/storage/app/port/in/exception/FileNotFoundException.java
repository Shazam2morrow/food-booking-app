package food.booking.app.storage.app.port.in.exception;

import lombok.Getter;

/**
 * Thrown when file was not found
 *
 * @author shazam2morrow
 */
public class FileNotFoundException extends RuntimeException {

    @Getter
    private final String fileSlug;

    public static final String CODE = "file.notfound";

    public FileNotFoundException(String fileSlug) {
        this.fileSlug = fileSlug;
    }

}

package food.booking.app.storage.app.port.out;

import food.booking.app.storage.domain.File;

/**
 * Create file port
 *
 * @author shazam2morrow
 */
public interface CreateFilePort {

    /**
     * Create file
     *
     * @param createFile create file
     * @return file
     */
    File create(CreateFile createFile);

}

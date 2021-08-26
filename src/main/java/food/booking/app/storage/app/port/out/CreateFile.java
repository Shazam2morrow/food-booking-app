package food.booking.app.storage.app.port.out;

import food.booking.app.storage.adapter.out.persistance.StorageType;

import java.net.URI;

/**
 * Create file
 *
 * @author shazam2morrow
 */
public record CreateFile(URI url,
                         Long size,
                         String slug,
                         String mimeType,
                         String checksum,
                         String originalName,
                         StorageType storage) {
}

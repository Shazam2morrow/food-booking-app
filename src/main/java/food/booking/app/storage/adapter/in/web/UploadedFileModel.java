package food.booking.app.storage.adapter.in.web;

import java.net.URI;
import java.time.Instant;

/**
 * Uploaded file model
 *
 * @author shazam2morrow
 */
record UploadedFileModel(URI url,
                         Long size,
                         String slug,
                         String mimeType,
                         String checksum,
                         Instant uploadedAt,
                         String originalName) {
}

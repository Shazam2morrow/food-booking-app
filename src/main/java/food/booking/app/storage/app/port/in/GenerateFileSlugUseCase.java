package food.booking.app.storage.app.port.in;

/**
 * Generate file slug use case
 *
 * @author shazam2morrow
 */
public interface GenerateFileSlugUseCase {

    /**
     * Generate unique slug for a new uploaded file
     *
     * @return uploaded file unique slug
     */
    String generateSlug();

}

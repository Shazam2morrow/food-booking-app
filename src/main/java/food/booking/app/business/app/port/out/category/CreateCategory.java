package food.booking.app.business.app.port.out.category;

import java.net.URI;

/**
 * Create category
 *
 * @author shazam2morrow
 */
public record CreateCategory(String slug, String title, Short sortOrder, URI iconUrl) {
}

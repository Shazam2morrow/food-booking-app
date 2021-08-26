package food.booking.app.business.app.port.out.group;

import java.net.URI;

/**
 * Update group details
 *
 * @author shazam2morrow
 */
public record UpdateGroupDetails(String slug, String title, Short sortOrder, URI iconUrl, Boolean active) {
}

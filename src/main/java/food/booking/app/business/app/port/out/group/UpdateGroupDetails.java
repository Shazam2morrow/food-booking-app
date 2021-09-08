package food.booking.app.business.app.port.out.group;

import food.booking.app.shared.Slugable;

import java.net.URI;

/**
 * Update group details
 *
 * @author shazam2morrow
 */
public record UpdateGroupDetails(
        String slug,
        String title,
        Short sortOrder,
        URI iconUrl,
        Boolean active) implements Slugable<String> {

    public String getSlug() {
        return slug;
    }

}

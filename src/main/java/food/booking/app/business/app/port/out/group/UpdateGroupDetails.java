package food.booking.app.business.app.port.out.group;

import food.booking.app.shared.HasSlug;

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
        Boolean active) implements HasSlug<String> {

    public String getSlug() {
        return slug;
    }

}

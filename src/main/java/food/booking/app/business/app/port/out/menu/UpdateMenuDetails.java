package food.booking.app.business.app.port.out.menu;

import food.booking.app.shared.Slugable;

/**
 * Update menu details
 *
 * @author shazam2morrow
 */
public record UpdateMenuDetails(
        String slug,
        String title,
        Short sortOrder,
        Boolean active) implements Slugable<String> {

    public String getSlug() {
        return slug;
    }

}

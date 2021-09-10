package food.booking.app.business.app.port.out.category;

import food.booking.app.shared.HasSlug;

import java.net.URI;

/**
 * Update category details
 *
 * @author shazam2morrow
 */
public record UpdateCategoryDetails(
        String slug,
        String title,
        Short sortOrder,
        Boolean active,
        URI iconUrl) implements HasSlug<String> {

    @Override
    public String getSlug() {
        return slug;
    }

}

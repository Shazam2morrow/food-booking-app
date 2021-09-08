package food.booking.app.business.app.port.in.category;

import food.booking.app.shared.validation.*;

import java.net.URI;

/**
 * Update category details command
 *
 * @author shazam2morrow
 */
public record UpdateCategoryDetailsCommand(
        @Slug String slug,
        @Title String title,
        @FileUrlOrNull URI iconUrl,
        @Active Boolean active,
        @SortOrder Short sortOrder) {

    public Boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "category";
    }

}

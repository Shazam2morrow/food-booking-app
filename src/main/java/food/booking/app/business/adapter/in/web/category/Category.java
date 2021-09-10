package food.booking.app.business.adapter.in.web.category;

import food.booking.app.shared.validation.FileUrl;
import food.booking.app.shared.validation.SortOrder;
import food.booking.app.shared.validation.Title;

import java.net.URI;

/**
 * Category
 *
 * @author shazam2morrow
 */
record Category(
        @FileUrl URI iconUrl,
        @Title String title,
        @SortOrder Short sortOrder) {
}

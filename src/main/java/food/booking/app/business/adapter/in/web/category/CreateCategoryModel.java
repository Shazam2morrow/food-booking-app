package food.booking.app.business.adapter.in.web.category;

import food.booking.app.shared.validation.Title;

import javax.annotation.Nullable;
import javax.validation.constraints.PositiveOrZero;
import java.net.URI;

/**
 * Create category model
 *
 * @author shazam2morrow
 */
record CreateCategoryModel(@Title String title,
                           @Nullable URI iconUrl,
                           @Nullable @PositiveOrZero(message = "sortOrder must not be negative") Short sortOrder) {
}

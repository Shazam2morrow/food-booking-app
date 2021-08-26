package food.booking.app.business.adapter.in.web.menu;

import food.booking.app.business.domain.Restaurant;
import food.booking.app.shared.validation.Title;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Create restaurant menu model
 *
 * @author shazam2morrow
 */
record CreateMenuModel(
        @Title String title,
        @NotNull(message = "restaurant can not be null") Restaurant restaurant,
        @Nullable @PositiveOrZero(message = "sortOrder can not be negative") Short sortOrder) {
}

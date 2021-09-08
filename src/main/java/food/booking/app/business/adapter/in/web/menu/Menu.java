package food.booking.app.business.adapter.in.web.menu;

import food.booking.app.business.domain.Restaurant;
import food.booking.app.shared.validation.SortOrder;
import food.booking.app.shared.validation.Title;

import javax.validation.constraints.NotNull;

/**
 * Menu
 *
 * @author shazam2morrow
 */
record Menu(
        @Title String title,
        @NotNull(message = "menu.restaurant.notnull") Restaurant restaurant,
        @SortOrder Short sortOrder) {
}

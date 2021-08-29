package food.booking.app.business.adapter.in.web.restaurant;

import food.booking.app.business.adapter.out.persistence.RestaurantType;
import food.booking.app.business.domain.Category;
import food.booking.app.shared.domain.DailySchedule;
import food.booking.app.shared.domain.Location;
import food.booking.app.shared.validation.Address;
import food.booking.app.shared.validation.Description;
import food.booking.app.shared.validation.ShortTitle;
import food.booking.app.shared.validation.Title;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

/**
 * Create restaurant model
 *
 * @author shazam2morrow
 */
record CreateRestaurantModel(
        @Title String title,
        @Address String address,
        @NotNull(message = "type can not be null") RestaurantType type,
        @NotNull(message = "schedule can not be null") List<DailySchedule> schedule,
        @Nullable URI bannerUrl,
        @Nullable List<URI> media,
        @Nullable Location location,
        @ShortTitle String shortTitle,
        @Nullable List<String> aliases,
        @Description String description,
        @Nullable BigDecimal averageReceipt,
        @Nullable List<Category> categories) {
}

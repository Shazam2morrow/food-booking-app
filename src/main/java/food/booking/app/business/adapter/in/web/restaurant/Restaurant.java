package food.booking.app.business.adapter.in.web.restaurant;


import food.booking.app.business.adapter.out.persistence.RestaurantType;
import food.booking.app.business.domain.Category;
import food.booking.app.shared.domain.DailySchedule;
import food.booking.app.shared.domain.Location;
import food.booking.app.shared.validation.*;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

/**
 * Restaurant model
 *
 * @author shazam2morrow
 */
record Restaurant(
        @Title String title,
        @Address String address,
        @Type RestaurantType type,
        @Schedule List<@Valid DailySchedule> schedule,
        @FileUrlOrNull URI bannerUrl,
        @FileUrlListOrNull List<URI> media,
        @ShortTitle String shortTitle,
        @Valid Location location,
        @Description String description,
        @Aliases List<String> aliases,
        @AvgReceipt BigDecimal averageReceipt,
        @Nullable List<Category> categories) {
}

package food.booking.app.business.adapter.in.web.item;

import food.booking.app.business.domain.Group;
import food.booking.app.shared.validation.Description;
import food.booking.app.shared.validation.Title;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.net.URI;

/**
 * Create item model
 *
 * @author shazam2morrow
 */
record CreateItemModel(@Title String title,

                       @NotNull(message = "group can not be null") Group group,

                       @Nullable
                       @PositiveOrZero(message = "price can not be negative")
                       BigDecimal price,

                       @Nullable
                       @PositiveOrZero(message = "calories can not be negative")
                       Integer calories,

                       @Nullable
                       @PositiveOrZero(message = "sortOrder can not be negative")
                       Integer sortOrder,

                       @Nullable
                       @PositiveOrZero(message = "cookingTime can not be negative")
                       Short cookingTime,

                       @Description String description,

                       @Nullable URI bannerUrl) {
}

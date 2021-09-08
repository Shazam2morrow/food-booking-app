package food.booking.app.business.adapter.in.web.item;

import food.booking.app.business.domain.Group;
import food.booking.app.shared.validation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.net.URI;

/**
 * Create item model
 *
 * @author shazam2morrow
 */
record Item(@Title String title,
            @NotNull(message = "item.group.notnull") Group group,
            @Price BigDecimal price,
            @Calories Integer calories,
            @SortOrder Integer sortOrder,
            @CookingTime Short cookingTime,
            @Description String description,
            @FileUrlOrNull URI bannerUrl) {
}

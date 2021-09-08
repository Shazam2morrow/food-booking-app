package food.booking.app.business.app.port.in.item;

import food.booking.app.shared.validation.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.net.URI;

/**
 * Update item details command
 *
 * @author shazam2morrow
 */
@Getter
@RequiredArgsConstructor
public class UpdateItemDetailsCommand {

    @Slug
    private final String slug;

    @Title
    private final String title;

    @FileUrlOrNull
    private final URI bannerUrl;

    @Description
    private final String description;

    @Active
    private final Boolean active;

    @Price
    private final BigDecimal price;

    @Calories
    private final Integer calories;

    @SortOrder
    private final Integer sortOrder;

    @CookingTime
    private final Short cookingTime;

    public String toString() {
        return "item";
    }

}

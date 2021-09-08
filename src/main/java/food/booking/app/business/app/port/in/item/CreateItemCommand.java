package food.booking.app.business.app.port.in.item;

import food.booking.app.business.domain.Group;
import food.booking.app.shared.validation.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.net.URI;

/**
 * Create item command
 *
 * @author shazam2morrow
 */
@Getter
@RequiredArgsConstructor
public class CreateItemCommand {

    @Title
    private final String title;

    @Description
    private final String description;

    @NotNull(message = "item.group.notnull")
    private final Group group;

    @Price
    private final BigDecimal price;

    @Calories
    private final Integer calories;

    @SortOrder
    private final Integer sortOrder;

    @CookingTime
    private final Short cookingTime;

    @FileUrlOrNull
    private final URI bannerUrl;

    @Override
    public String toString() {
        return "item";
    }

}

package food.booking.app.business.app.port.in.item;

import food.booking.app.business.domain.Group;
import food.booking.app.shared.SelfValidate;
import food.booking.app.shared.validation.Description;
import food.booking.app.shared.validation.Title;
import lombok.Getter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.net.URI;

/**
 * Create item command
 *
 * @author shazam2morrow
 */
@Getter
public class CreateItemCommand extends SelfValidate<CreateItemCommand> {

    @Title
    private final String title;

    @Description
    private final String description;

    @NotNull(message = "group can not be null")
    private final Group group;

    @Nullable
    @PositiveOrZero(message = "price can not be negative")
    private final BigDecimal price;

    @Nullable
    @PositiveOrZero(message = "calories can not be negative")
    private final Integer calories;

    @Nullable
    @PositiveOrZero(message = "sortOrder can not be negative")
    private final Integer sortOrder;

    @Nullable
    @PositiveOrZero(message = "cookingTime can not be negative")
    private final Short cookingTime;

    @Nullable
    private final URI bannerUrl;

    public CreateItemCommand(Group group,
                             String title,
                             @Nullable URI bannerUrl,
                             @Nullable BigDecimal price,
                             @Nullable Integer calories,
                             @Nullable Integer sortOrder,
                             @Nullable Short cookingTime,
                             @Nullable String description) {
        this.title = title;
        this.group = group;
        this.price = price;
        this.calories = calories;
        this.bannerUrl = bannerUrl;
        this.sortOrder = sortOrder;
        this.cookingTime = cookingTime;
        this.description = description;
        selfValidate();
    }

}

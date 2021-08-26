package food.booking.app.business.app.port.in.item;

import food.booking.app.shared.SelfValidate;
import food.booking.app.shared.validation.Description;
import food.booking.app.shared.validation.Slug;
import food.booking.app.shared.validation.Title;
import lombok.Getter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.net.URI;

/**
 * Update item details command
 *
 * @author shazam2morrow
 */
@Getter
public class UpdateItemDetailsCommand extends SelfValidate<UpdateItemDetailsCommand> {

    @Slug
    private final String slug;

    @Title
    private final String title;

    @Nullable
    private final URI bannerUrl;

    @Description
    private final String description;

    @NotNull(message = "active can not be null")
    private final Boolean active;

    @PositiveOrZero(message = "price can not be negative")
    private final BigDecimal price;

    @PositiveOrZero(message = "calories can not be negative")
    private final Integer calories;

    @PositiveOrZero(message = "sortOrder can not be negative")
    private final Integer sortOrder;

    @PositiveOrZero(message = "cookingTime can not be negative")
    private final Short cookingTime;

    public UpdateItemDetailsCommand(String slug,
                                    String title,
                                    Boolean active,
                                    BigDecimal price,
                                    Integer calories,
                                    Integer sortOrder,
                                    Short cookingTime,
                                    @Nullable URI bannerUrl,
                                    @Nullable String description) {
        this.slug = slug;
        this.price = price;
        this.title = title;
        this.active = active;
        this.calories = calories;
        this.bannerUrl = bannerUrl;
        this.sortOrder = sortOrder;
        this.description = description;
        this.cookingTime = cookingTime;
        selfValidate();
    }

}

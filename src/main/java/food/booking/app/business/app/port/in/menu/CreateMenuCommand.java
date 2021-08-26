package food.booking.app.business.app.port.in.menu;

import food.booking.app.business.domain.Restaurant;
import food.booking.app.shared.SelfValidate;
import food.booking.app.shared.validation.Title;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Create menu command
 *
 * @author shazam2morrow
 */
@Getter
public class CreateMenuCommand extends SelfValidate<CreateMenuCommand> {

    @Title
    private final String title;

    @NotNull(message = "restaurant can not be null")
    private final Restaurant restaurant;

    @PositiveOrZero(message = "sortOrder can be negative")
    private final Short sortOrder;

    public CreateMenuCommand(String title, Short sortOrder, Restaurant restaurant) {
        this.title = title;
        this.sortOrder = sortOrder;
        this.restaurant = restaurant;
        selfValidate();
    }

}

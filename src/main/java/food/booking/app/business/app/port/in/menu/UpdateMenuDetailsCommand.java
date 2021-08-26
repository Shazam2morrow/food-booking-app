package food.booking.app.business.app.port.in.menu;

import food.booking.app.shared.SelfValidate;
import food.booking.app.shared.validation.Slug;
import food.booking.app.shared.validation.Title;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Update menu details command
 *
 * @author shazam2morrow
 */
@Getter
public class UpdateMenuDetailsCommand extends SelfValidate<UpdateMenuDetailsCommand> {

    @Slug
    private final String slug;

    @Title
    private final String title;

    @NotNull(message = "active can not be null")
    private final Boolean active;

    @PositiveOrZero(message = "sortOrder can not be negative")
    private final Short sortOrder;

    public UpdateMenuDetailsCommand(String slug, String title, Short sortOrder, Boolean active) {
        this.slug = slug;
        this.title = title;
        this.active = active;
        this.sortOrder = sortOrder;
        selfValidate();
    }

}

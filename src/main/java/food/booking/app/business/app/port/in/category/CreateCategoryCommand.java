package food.booking.app.business.app.port.in.category;

import food.booking.app.shared.SelfValidate;
import food.booking.app.shared.validation.Title;
import lombok.Getter;

import javax.annotation.Nullable;
import javax.validation.constraints.PositiveOrZero;
import java.net.URI;

/**
 * Create category command
 *
 * @author shazam2morrow
 */
@Getter
public class CreateCategoryCommand extends SelfValidate<CreateCategoryCommand> {

    @Title
    private final String title;

    @Nullable
    private final URI iconUrl;

    @Nullable
    @PositiveOrZero(message = "sortOrder can not be negative")
    private final Short sortOrder;

    public CreateCategoryCommand(String title, @Nullable URI iconUrl, @Nullable Short sortOrder) {
        this.title = title;
        this.iconUrl = iconUrl;
        this.sortOrder = sortOrder;
        selfValidate();
    }

}

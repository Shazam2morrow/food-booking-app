package food.booking.app.business.app.port.in.category;

import food.booking.app.shared.SelfValidate;
import food.booking.app.shared.validation.Slug;
import food.booking.app.shared.validation.Title;
import lombok.Getter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.net.URI;

/**
 * Update category details command
 *
 * @author shazam2morrow
 */
@Getter
public class UpdateCategoryDetailsCommand extends SelfValidate<UpdateCategoryDetailsCommand> {

    @Slug
    private final String slug;

    @Title
    private final String title;

    @NotNull(message = "active can not be null")
    private final Boolean active;

    @PositiveOrZero(message = "sortOrder can not be negative")
    private final Short sortOrder;

    @Nullable
    private final URI iconUrl;

    public UpdateCategoryDetailsCommand(String slug,
                                        String title,
                                        Boolean active,
                                        Short sortOrder,
                                        @Nullable URI iconUrl) {
        this.slug = slug;
        this.title = title;
        this.active = active;
        this.iconUrl = iconUrl;
        this.sortOrder = sortOrder;
    }

    public Boolean isActive() {
        return active;
    }

}

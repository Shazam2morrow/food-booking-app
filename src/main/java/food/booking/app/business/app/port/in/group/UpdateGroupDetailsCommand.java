package food.booking.app.business.app.port.in.group;

import food.booking.app.shared.SelfValidate;
import food.booking.app.shared.validation.Slug;
import food.booking.app.shared.validation.Title;
import lombok.Getter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.net.URI;

/**
 * Update group details command
 *
 * @author shazam2morrow
 */
@Getter
public class UpdateGroupDetailsCommand extends SelfValidate<UpdateGroupDetailsCommand> {

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

    public UpdateGroupDetailsCommand(String slug,
                                     String title,
                                     Short sortOrder,
                                     Boolean active,
                                     @Nullable URI iconUrl) {
        this.slug = slug;
        this.title = title;
        this.active = active;
        this.iconUrl = iconUrl;
        this.sortOrder = sortOrder;
        selfValidate();
    }

    public Boolean isActive() {
        return active;
    }

}

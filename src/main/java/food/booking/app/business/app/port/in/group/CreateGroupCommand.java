package food.booking.app.business.app.port.in.group;

import food.booking.app.business.domain.Menu;
import food.booking.app.shared.SelfValidate;
import food.booking.app.shared.validation.Title;
import lombok.Getter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.net.URI;

/**
 * Create group command
 *
 * @author shazam2morrow
 */
@Getter
public class CreateGroupCommand extends SelfValidate<CreateGroupCommand> {

    @Title
    private final String title;

    @NotNull(message = "menu can not be null")
    private final Menu menu;

    @Nullable
    private final URI iconUrl;

    @Nullable
    private final Short sortOrder;

    public CreateGroupCommand(Menu menu, String title, @Nullable Short sortOrder, @Nullable URI iconUrl) {
        this.menu = menu;
        this.title = title;
        this.iconUrl = iconUrl;
        this.sortOrder = sortOrder;
        selfValidate();
    }

}

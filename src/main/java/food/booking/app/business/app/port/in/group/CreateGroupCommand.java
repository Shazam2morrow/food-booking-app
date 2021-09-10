package food.booking.app.business.app.port.in.group;

import food.booking.app.business.domain.Menu;
import food.booking.app.shared.validation.FileUrl;
import food.booking.app.shared.validation.SortOrder;
import food.booking.app.shared.validation.Title;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.net.URI;

/**
 * Create group command
 *
 * @author shazam2morrow
 */
@Getter
@RequiredArgsConstructor
public class CreateGroupCommand {

    @Title
    private final String title;

    @FileUrl
    private final URI iconUrl;

    @NotNull(message = "group.menu.notnull")
    private final Menu menu;

    @SortOrder
    private final Short sortOrder;

    @Override
    public String toString() {
        return "group";
    }

}

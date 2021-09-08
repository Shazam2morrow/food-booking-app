package food.booking.app.business.app.port.in.group;

import food.booking.app.shared.validation.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.net.URI;

/**
 * Update group details command
 *
 * @author shazam2morrow
 */
@Getter
@RequiredArgsConstructor
public class UpdateGroupDetailsCommand {

    @Slug
    private final String slug;

    @Title
    private final String title;

    @Active
    private final Boolean active;

    @SortOrder
    private final Short sortOrder;

    @FileUrlOrNull
    private final URI iconUrl;

    public Boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "group";
    }

}

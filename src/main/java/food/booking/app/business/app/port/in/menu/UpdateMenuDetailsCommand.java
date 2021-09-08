package food.booking.app.business.app.port.in.menu;

import food.booking.app.shared.validation.Active;
import food.booking.app.shared.validation.Slug;
import food.booking.app.shared.validation.SortOrder;
import food.booking.app.shared.validation.Title;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Update menu details command
 *
 * @author shazam2morrow
 */
@Getter
@RequiredArgsConstructor
public class UpdateMenuDetailsCommand {

    @Slug
    private final String slug;

    @Title
    private final String title;

    @Active
    private final Boolean active;

    @SortOrder
    private final Short sortOrder;

}

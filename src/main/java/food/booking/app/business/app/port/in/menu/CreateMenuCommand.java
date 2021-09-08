package food.booking.app.business.app.port.in.menu;

import food.booking.app.business.domain.Restaurant;
import food.booking.app.shared.validation.SortOrder;
import food.booking.app.shared.validation.Title;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Create menu command
 *
 * @author shazam2morrow
 */
@Getter
@RequiredArgsConstructor
public class CreateMenuCommand {

    @Title
    private final String title;

    @NotNull(message = "menu.restaurant.notnull")
    private final Restaurant restaurant;

    @SortOrder
    private final Short sortOrder;

}

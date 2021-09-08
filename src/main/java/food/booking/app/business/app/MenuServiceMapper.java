package food.booking.app.business.app;

import food.booking.app.business.app.port.in.menu.CreateMenuCommand;
import food.booking.app.business.app.port.in.menu.UpdateMenuDetailsCommand;
import food.booking.app.business.app.port.out.menu.CheckMenuSlugPort;
import food.booking.app.business.app.port.out.menu.CreateMenu;
import food.booking.app.business.app.port.out.menu.UpdateMenuDetails;
import food.booking.app.business.domain.Menu;
import food.booking.app.shared.size.SlugSize;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;

/**
 * Menu service mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class MenuServiceMapper {

    private final CheckMenuSlugPort checkMenuSlugPort;

    private final RandomStringGenerator randomStringGenerator;

    /**
     * Map to create menu
     *
     * @param command create menu command
     * @return create menu
     */
    CreateMenu mapToCreateMenu(CreateMenuCommand command) {
        return new CreateMenu(
                generateSlug(),
                command.getTitle(),
                command.getSortOrder(),
                command.getRestaurant());
    }

    /**
     * Map to update menu details command
     *
     * @param menu menu
     * @return update menu details command
     */
    UpdateMenuDetailsCommand mapToUpdateMenuDetailsCommand(Menu menu) {
        return new UpdateMenuDetailsCommand(
                menu.getSlug(),
                menu.getTitle(),
                menu.isActive(),
                menu.getSortOrder());
    }

    /**
     * Map to update menu details
     *
     * @param command update menu details command
     * @return update menu details
     */
    UpdateMenuDetails mapToUpdateMenuDetails(UpdateMenuDetailsCommand command) {
        return new UpdateMenuDetails(
                command.getSlug(),
                command.getTitle(),
                command.getSortOrder(),
                command.getActive());
    }

    /**
     * Generate unique slug for a new menu
     *
     * @return menu slug
     */
    private String generateSlug() {
        String slug;
        do {
            slug = randomStringGenerator.generate(SlugSize.MAX);
        } while (checkMenuSlugPort.checkBySlug(slug));
        return slug;
    }

}

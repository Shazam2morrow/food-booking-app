package food.booking.app.business.adapter.in.web.menu;

import food.booking.app.business.app.port.in.menu.CreateMenuCommand;

/**
 * Menu web mapper
 *
 * @author shazam2morrow
 */
class MenuWebMapper {

    /**
     * Map to command
     *
     * @param model create menu model
     * @return create menu command
     */
    CreateMenuCommand mapToCommand(CreateMenuModel model) {
        return new CreateMenuCommand(model.title(), model.sortOrder(), model.restaurant());
    }

}

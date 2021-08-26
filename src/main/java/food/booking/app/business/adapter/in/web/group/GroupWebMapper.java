package food.booking.app.business.adapter.in.web.group;

import food.booking.app.business.app.port.in.group.CreateGroupCommand;

/**
 * Group web mapper
 *
 * @author shazam2morrow
 */
class GroupWebMapper {

    /**
     * Map to command
     *
     * @param model create group model
     * @return create group command
     */
    CreateGroupCommand mapToCommand(CreateGroupModel model) {
        return new CreateGroupCommand(model.menu(), model.title(), model.sortOrder(), model.iconUrl());
    }

}

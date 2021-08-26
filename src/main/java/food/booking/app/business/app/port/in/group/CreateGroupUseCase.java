package food.booking.app.business.app.port.in.group;

import food.booking.app.business.domain.Group;

/**
 * Create group use case
 *
 * @author shazam2morrow
 */
public interface CreateGroupUseCase {

    /**
     * Create group
     *
     * @param command create group command
     * @return group
     */
    Group create(CreateGroupCommand command);

}

package food.booking.app.business.app.port.out.group;

import food.booking.app.business.domain.Group;

/**
 * Create group port
 *
 * @author shazam2morrow
 */
public interface CreateGroupPort {

    /**
     * Create group
     *
     * @param createGroup create group
     * @return group
     */
    Group create(CreateGroup createGroup);

}

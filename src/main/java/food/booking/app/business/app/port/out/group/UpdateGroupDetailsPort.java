package food.booking.app.business.app.port.out.group;

import food.booking.app.business.app.port.in.group.exception.GroupNotFoundException;

/**
 * Update group details port
 *
 * @author shazam2morrow
 */
public interface UpdateGroupDetailsPort {

    /**
     * Update group details
     *
     * @param details update group details
     * @throws GroupNotFoundException if group was not found
     */
    void update(UpdateGroupDetails details);

}

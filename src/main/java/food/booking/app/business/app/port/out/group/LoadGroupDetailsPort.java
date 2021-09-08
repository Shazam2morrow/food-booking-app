package food.booking.app.business.app.port.out.group;

import food.booking.app.business.app.port.in.group.exception.GroupNotFoundException;
import food.booking.app.business.domain.Group;

/**
 * Load group details port
 *
 * @author shazam2morrow
 */
public interface LoadGroupDetailsPort {

    /**
     * Load group details
     *
     * @param groupSlug group slug
     * @return group details
     * @throws GroupNotFoundException if group was not found
     */
    Group loadDetailsBySlug(String groupSlug);

}

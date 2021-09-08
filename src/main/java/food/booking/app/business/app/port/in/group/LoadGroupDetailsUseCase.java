package food.booking.app.business.app.port.in.group;

import food.booking.app.business.app.port.in.group.exception.GroupNotFoundException;
import food.booking.app.business.domain.Group;

/**
 * Load group details use case
 *
 * @author shazam2morrow
 */
public interface LoadGroupDetailsUseCase {

    /**
     * Load group details
     *
     * @param groupSlug group slug
     * @return group details
     * @throws GroupNotFoundException if group was not found
     */
    Group loadDetailsBySlug(String groupSlug);

}

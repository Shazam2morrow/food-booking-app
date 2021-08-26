package food.booking.app.business.app.port.out.group;

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
     */
    Group loadDetailsBySlug(String groupSlug);

}

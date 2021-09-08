package food.booking.app.business.app.port.in.group;

import food.booking.app.business.app.port.in.group.exception.GroupNotFoundException;

/**
 * Delete group use case
 *
 * @author shazam2morrow
 */
public interface DeleteGroupUseCase {

    /**
     * Delete group use case
     *
     * @param groupSlug group slug
     * @throws GroupNotFoundException if group was not found
     */
    void deleteBySlug(String groupSlug);

}

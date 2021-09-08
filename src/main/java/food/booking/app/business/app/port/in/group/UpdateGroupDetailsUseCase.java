package food.booking.app.business.app.port.in.group;

import food.booking.app.business.app.port.in.group.exception.GroupNotFoundException;

import javax.validation.Valid;

/**
 * Update group details use case
 *
 * @author shazam2morrow
 */
public interface UpdateGroupDetailsUseCase {

    /**
     * Update group details
     *
     * @param command update group details command
     * @throws GroupNotFoundException if group was not found
     */
    void update(@Valid UpdateGroupDetailsCommand command);

}

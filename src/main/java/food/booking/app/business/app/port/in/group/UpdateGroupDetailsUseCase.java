package food.booking.app.business.app.port.in.group;

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
     */
    void update(UpdateGroupDetailsCommand command);

}

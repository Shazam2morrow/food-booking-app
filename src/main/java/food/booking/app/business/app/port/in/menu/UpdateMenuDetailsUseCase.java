package food.booking.app.business.app.port.in.menu;

/**
 * Update menu details use case
 *
 * @author shazam2morrow
 */
public interface UpdateMenuDetailsUseCase {

    /**
     * Update menu details
     *
     * @param command update menu details command
     */
    void update(UpdateMenuDetailsCommand command);

}

package food.booking.app.business.app.port.in.menu;

/**
 * Delete menu use case
 *
 * @author shazam2morrow
 */
public interface DeleteMenuUseCase {

    /**
     * Delete menu
     *
     * @param menuSlug menu slug
     */
    void deleteBySlug(String menuSlug);

}

package food.booking.app.business.app.port.out.menu;

/**
 * Check menu slug port
 *
 * @author shazam2morrow
 */
public interface CheckMenuSlugPort {

    /**
     * Check if menu already exists
     *
     * @param menuSlug menu slug
     * @return true if menu already exists or false otherwise
     */
    boolean checkBySlug(String menuSlug);

}

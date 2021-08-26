package food.booking.app.business.app.port.out.item;

/**
 * Check item slug port
 *
 * @author shazam2morrow
 */
public interface CheckItemSlugPort {

    /**
     * Check if item already exists
     *
     * @param itemSlug item slug
     * @return true if item already exists otherwise false
     */
    boolean checkBySlug(String itemSlug);

}

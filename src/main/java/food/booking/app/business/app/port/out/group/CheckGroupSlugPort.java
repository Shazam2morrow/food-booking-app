package food.booking.app.business.app.port.out.group;

/**
 * Check menu group slug port
 *
 * @author shazam2morrow
 */
public interface CheckGroupSlugPort {

    /**
     * Check if group already exists
     *
     * @param groupSlug group slug
     * @return true if group already exists otherwise false
     */
    boolean checkBySlug(String groupSlug);

}

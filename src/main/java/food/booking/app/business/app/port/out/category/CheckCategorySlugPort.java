package food.booking.app.business.app.port.out.category;

/**
 * Check category port
 *
 * @author shazam2morrow
 */
public interface CheckCategorySlugPort {

    /**
     * Check if category with the given slug exists
     *
     * @param categorySlug category slug
     * @return true if category exists or false otherwise
     */
    boolean checkBySlug(String categorySlug);

}

package food.booking.app.business.app.port.out.category;

import food.booking.app.shared.CanCheckSlug;

/**
 * Check category port
 *
 * @author shazam2morrow
 */
public interface CheckCategoryCanCheckSlugPort extends CanCheckSlug {

    /**
     * Check if category with the given slug exists
     *
     * @param categorySlug category slug
     * @return true if category exists or false otherwise
     */
    default boolean checkBySlug(String categorySlug) {
        return check(categorySlug);
    }

}

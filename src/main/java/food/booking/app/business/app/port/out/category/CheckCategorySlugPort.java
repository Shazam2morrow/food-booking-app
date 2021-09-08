package food.booking.app.business.app.port.out.category;

import food.booking.app.shared.SlugCheckable;

/**
 * Check category port
 *
 * @author shazam2morrow
 */
public interface CheckCategorySlugPort extends SlugCheckable {

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

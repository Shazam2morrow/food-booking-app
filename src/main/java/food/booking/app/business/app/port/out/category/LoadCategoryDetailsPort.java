package food.booking.app.business.app.port.out.category;

import food.booking.app.business.domain.Category;

/**
 * Load category details port
 *
 * @author shazam2morrow
 */
public interface LoadCategoryDetailsPort {

    /**
     * Load category details
     *
     * @param categorySlug category slug
     * @return category details
     */
    Category loadDetailsBySlug(String categorySlug);

}

package food.booking.app.business.app.port.out.category;

import food.booking.app.business.app.port.in.category.exception.CategoryNotFoundException;
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
     * @throws CategoryNotFoundException if category was not found
     */
    Category loadDetailsBySlug(String categorySlug);

}

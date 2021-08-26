package food.booking.app.business.app.port.in.category;

import food.booking.app.business.domain.Category;

/**
 * Load category details use case
 *
 * @author shazam2morrow
 */
public interface LoadCategoryDetailsUseCase {

    /**
     * Load category details
     *
     * @param categorySlug category slug
     * @return category details
     */
    Category loadDetailsBySlug(String categorySlug);

}

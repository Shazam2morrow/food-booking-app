package food.booking.app.business.app.port.in.category;

import food.booking.app.business.domain.Category;

import java.util.List;

/**
 * Load category list use case
 *
 * @author shazam2morrow
 */
public interface LoadCategoryListUseCase {

    /**
     * Load list of categories
     *
     * @return categories
     */
    List<Category> loadList();

}

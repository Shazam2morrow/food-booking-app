package food.booking.app.business.app.port.out.category;

import food.booking.app.business.domain.Category;

import java.util.List;

/**
 * Load category list port
 *
 * @author shazam2morrow
 */
public interface LoadCategoryListPort {

    /**
     * Load list of categories
     *
     * @return list of categories
     */
    List<Category> loadList();

}

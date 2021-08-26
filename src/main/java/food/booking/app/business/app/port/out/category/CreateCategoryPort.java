package food.booking.app.business.app.port.out.category;

import food.booking.app.business.domain.Category;

/**
 * Create category port
 *
 * @author shazam2morrow
 */
public interface CreateCategoryPort {

    /**
     * Create category
     *
     * @param createCategory create category
     * @return category
     */
    Category create(CreateCategory createCategory);

}

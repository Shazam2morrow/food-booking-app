package food.booking.app.business.app.port.out.category;

import food.booking.app.business.app.port.in.category.exception.CategoryNotFoundException;

/**
 * Update category details port
 *
 * @author shazam2morrow
 */
public interface UpdateCategoryDetailsPort {

    /**
     * Update category details
     *
     * @param details update category details
     * @throws CategoryNotFoundException if category was not found
     */
    void update(UpdateCategoryDetails details);

}

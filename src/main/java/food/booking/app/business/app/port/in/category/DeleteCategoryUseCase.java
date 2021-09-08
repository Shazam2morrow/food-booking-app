package food.booking.app.business.app.port.in.category;

import food.booking.app.business.app.port.in.category.exception.CategoryNotFoundException;
import food.booking.app.business.app.port.in.category.exception.CategoryServiceException;

/**
 * Delete category use case
 *
 * @author shazam2morrow
 */
public interface DeleteCategoryUseCase {

    /**
     * Delete category
     *
     * @param categorySlug category slug
     * @throws CategoryNotFoundException if category was not found
     * @throws CategoryServiceException  if category could not be deleted
     */
    void deleteBySlug(String categorySlug);

}

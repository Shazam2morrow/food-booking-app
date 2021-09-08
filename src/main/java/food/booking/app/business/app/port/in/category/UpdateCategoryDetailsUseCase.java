package food.booking.app.business.app.port.in.category;

import food.booking.app.business.app.port.in.category.exception.CategoryServiceException;

import javax.validation.Valid;

/**
 * Update category details use case
 *
 * @author shazam2morrow
 */
public interface UpdateCategoryDetailsUseCase {

    /**
     * Update category details command
     *
     * @param command update category details command
     * @throws CategoryServiceException if category could not be updated
     */
    void update(@Valid UpdateCategoryDetailsCommand command);

}

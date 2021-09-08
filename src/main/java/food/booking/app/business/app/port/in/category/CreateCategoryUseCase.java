package food.booking.app.business.app.port.in.category;

import food.booking.app.business.app.port.in.category.exception.CategoryServiceException;
import food.booking.app.business.domain.Category;

import javax.validation.Valid;

/**
 * Create category use case
 *
 * @author shazam2morrow
 */
public interface CreateCategoryUseCase {

    /**
     * Create category
     *
     * @param command create category command
     * @return created category
     * @throws CategoryServiceException if category could not be created
     */
    Category create(@Valid CreateCategoryCommand command);

}

package food.booking.app.business.app.port.in.category;

import food.booking.app.business.domain.Category;

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
     * @return category
     */
    Category create(CreateCategoryCommand command);

}

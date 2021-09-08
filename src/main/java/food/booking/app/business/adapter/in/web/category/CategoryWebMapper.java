package food.booking.app.business.adapter.in.web.category;

import food.booking.app.business.app.port.in.category.CreateCategoryCommand;

/**
 * Category web mapper
 *
 * @author shazam2morrow
 */
class CategoryWebMapper {

    /**
     * Map to command
     *
     * @param category category
     * @return create category command
     */
    CreateCategoryCommand mapToCommand(Category category) {
        return new CreateCategoryCommand(
                category.iconUrl(),
                category.title(),
                category.sortOrder());
    }

}

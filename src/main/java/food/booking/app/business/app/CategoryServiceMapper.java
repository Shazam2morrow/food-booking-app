package food.booking.app.business.app;

import food.booking.app.business.app.port.in.category.CreateCategoryCommand;
import food.booking.app.business.app.port.in.category.UpdateCategoryDetailsCommand;
import food.booking.app.business.app.port.out.category.CreateCategory;
import food.booking.app.business.app.port.out.category.UpdateCategoryDetails;
import food.booking.app.business.domain.Category;
import food.booking.app.shared.SlugCheckable;
import food.booking.app.shared.SlugGenerator;
import food.booking.app.storage.app.FileUrlResolver;
import food.booking.app.storage.app.port.in.exception.FileUrlNotFoundException;
import org.apache.commons.text.RandomStringGenerator;

/**
 * Category service mapper
 *
 * @author shazam2morrow
 */
class CategoryServiceMapper extends SlugGenerator {

    private final FileUrlResolver fileUrlResolver;

    public CategoryServiceMapper(
            SlugCheckable slugCheckable,
            FileUrlResolver fileUrlResolver,
            RandomStringGenerator randomStringGenerator) {
        super(slugCheckable, randomStringGenerator);
        this.fileUrlResolver = fileUrlResolver;
    }

    /**
     * Map to create category
     *
     * @param command create category command
     * @return create category
     * @throws FileUrlNotFoundException if icon was not found
     */
    CreateCategory mapToCreateCategory(CreateCategoryCommand command) {
        return new CreateCategory(
                generateSlug(),
                command.getTitle(),
                command.getSortOrder(),
                fileUrlResolver.resolve(command.getIconUrl()));
    }

    /**
     * Map to update category details
     *
     * @param command update category details command
     * @return update category details
     * @throws FileUrlNotFoundException if icon was not found
     */
    UpdateCategoryDetails mapToUpdateCategoryDetails(UpdateCategoryDetailsCommand command) {
        return new UpdateCategoryDetails(
                command.slug(),
                command.title(),
                command.sortOrder(),
                command.isActive(),
                fileUrlResolver.resolve(command.iconUrl()));
    }

    /**
     * Map to update category details command
     *
     * @param category category
     * @return update category details command
     */
    UpdateCategoryDetailsCommand mapToUpdateCategoryDetailsCommand(Category category) {
        return new UpdateCategoryDetailsCommand(
                category.getSlug(),
                category.getTitle(),
                category.getIconUrl(),
                category.isActive(),
                category.getSortOrder());
    }

}

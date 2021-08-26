package food.booking.app.business.app;

import food.booking.app.business.app.port.in.category.CreateCategoryCommand;
import food.booking.app.business.app.port.in.category.UpdateCategoryDetailsCommand;
import food.booking.app.business.app.port.out.category.CheckCategorySlugPort;
import food.booking.app.business.app.port.out.category.CreateCategory;
import food.booking.app.business.app.port.out.category.UpdateCategoryDetails;
import food.booking.app.business.domain.Category;
import food.booking.app.shared.size.SlugSize;
import food.booking.app.storage.app.FileUriResolver;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;

/**
 * Category service mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class CategoryServiceMapper {

    private final FileUriResolver fileUriResolver;

    private final CheckCategorySlugPort checkCategorySlugPort;

    private final RandomStringGenerator randomStringGenerator;

    CreateCategory mapToCreateCategory(CreateCategoryCommand command) {
        return new CreateCategory(
                generateSlug(),
                command.getTitle(),
                command.getSortOrder(),
                fileUriResolver.resolve(command.getIconUrl()));
    }

    UpdateCategoryDetails mapToUpdateCategoryDetails(UpdateCategoryDetailsCommand command) {
        return new UpdateCategoryDetails(
                command.getSlug(),
                command.getTitle(),
                command.getSortOrder(),
                command.isActive(),
                fileUriResolver.resolve(command.getIconUrl()));
    }

    UpdateCategoryDetailsCommand mapToUpdateCategoryDetailsCommand(Category category) {
        return new UpdateCategoryDetailsCommand(
                category.getSlug(),
                category.getTitle(),
                category.isActive(),
                category.getSortOrder(),
                category.getIconUrl());
    }

    /**
     * Generate unique slug for a new category
     *
     * @return category slug
     */
    private String generateSlug() {
        String slug;
        do {
            slug = randomStringGenerator.generate(SlugSize.MAX);
        } while (checkCategorySlugPort.checkBySlug(slug));
        return slug;
    }

}

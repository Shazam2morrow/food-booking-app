package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.out.category.UpdateCategoryDetails;
import food.booking.app.business.domain.Category;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;

import javax.persistence.EntityNotFoundException;

/**
 * Category persistence resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class CategoryPersistenceResolver {

    private final CategoryRepository categoryRepository;

    CategoryJpaEntity resolve(Category category) {
        return resolve(requireValid(category).getSlug());
    }

    CategoryJpaEntity resolve(UpdateCategoryDetails details) {
        return resolve(requireValid(details).slug());
    }

    CategoryJpaEntity resolve(String categorySlug) {
        return categoryRepository.findBySlug(requireValidSlug(categorySlug))
                .orElseThrow(() -> new EntityNotFoundException("Category was not found " + categorySlug));
    }

    /**
     * Validate object
     *
     * @param category category
     * @return validated object
     */
    private Category requireValid(Category category) {
        return Validate.notNull(category, "category can not be null");
    }

    /**
     * Validate object
     *
     * @param details update category details
     * @return validated object
     */
    private UpdateCategoryDetails requireValid(UpdateCategoryDetails details) {
        return Validate.notNull(details, "updateCategoryDetails can not be null");
    }

    /**
     * Validate category slug
     *
     * @param categorySlug category slug
     * @return validated category slug
     */
    private String requireValidSlug(String categorySlug) {
        return Validate.notBlank(categorySlug, "categorySlug can not be blank");
    }

}

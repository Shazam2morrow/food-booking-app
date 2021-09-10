package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.in.category.exception.CategoryNotFoundException;
import food.booking.app.shared.HasSlug;
import lombok.RequiredArgsConstructor;

/**
 * Category persistence resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class CategoryPersistenceResolver {

    private final CategoryRepository categoryRepository;

    /**
     * Resolve category entity
     *
     * @param hasSlug slugable
     * @return resolved category entity
     * @throws CategoryNotFoundException if category was not found
     */
    CategoryJpaEntity resolve(HasSlug<String> hasSlug) {
        return resolve(hasSlug.getSlug());
    }

    /**
     * Resolve category entity
     *
     * @param categorySlug category slug
     * @return resolved category entity
     * @throws CategoryNotFoundException if category was not found
     */
    CategoryJpaEntity resolve(String categorySlug) {
        return categoryRepository.findBySlug(categorySlug)
                .orElseThrow(() -> new CategoryNotFoundException(categorySlug));
    }

}

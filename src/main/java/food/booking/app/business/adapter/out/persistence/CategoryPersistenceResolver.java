package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.in.category.exception.CategoryNotFoundException;
import food.booking.app.shared.Slugable;
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
     * @param slugable slugable
     * @return resolved category entity
     * @throws CategoryNotFoundException if category was not found
     */
    CategoryJpaEntity resolve(Slugable<String> slugable) {
        return resolve(slugable.getSlug());
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

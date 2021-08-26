package food.booking.app.business.adapter.out.persistance;

import food.booking.app.business.app.port.out.category.*;
import food.booking.app.business.domain.Category;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Category persistance adapter
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class CategorySlugPersistanceAdapter implements CreateCategoryPort,
        CheckCategorySlugPort,
        LoadCategoryListPort,
        LoadCategoryDetailsPort, UpdateCategoryDetailsPort {

    private final CategoryRepository categoryRepository;

    private final CategoryPersistanceMapper categoryPersistanceMapper;

    private final CategoryPersistanceResolver categoryPersistanceResolver;

    @Override
    public Category create(CreateCategory createCategory) {
        CategoryJpaEntity category = categoryPersistanceMapper.mapToJpaEntity(requireValid(createCategory));
        return categoryPersistanceMapper.mapToDomainEntity(categoryRepository.save(category));
    }

    @Override
    public void update(UpdateCategoryDetails details) {
        CategoryJpaEntity category = categoryPersistanceResolver.resolve(requireValid(details));
        categoryPersistanceMapper.applyUpdatedDetails(category, details);
    }

    @Override
    public boolean checkBySlug(String categorySlug) {
        return categoryRepository.existsBySlug(requireValidSlug(categorySlug));
    }

    @Override
    public List<Category> loadList() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryPersistanceMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Category loadDetailsBySlug(String categorySlug) {
        CategoryJpaEntity category = categoryPersistanceResolver.resolve(requireValidSlug(categorySlug));
        return categoryPersistanceMapper.mapToDomainEntity(category);
    }

    /**
     * Validate object
     *
     * @param createCategory create category
     * @return validate object
     */
    private CreateCategory requireValid(CreateCategory createCategory) {
        return Validate.notNull(createCategory, "createCategory can not be null");
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
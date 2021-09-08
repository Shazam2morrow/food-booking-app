package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.out.category.*;
import food.booking.app.business.domain.Category;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Category persistence adapter
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class CategoryPersistenceAdapter implements CreateCategoryPort,
        LoadCategoryListPort,
        CheckCategorySlugPort,
        LoadCategoryDetailsPort,
        UpdateCategoryDetailsPort {

    private final CategoryRepository categoryRepository;

    private final CategoryPersistenceMapper categoryPersistenceMapper;

    private final CategoryPersistenceResolver categoryPersistenceResolver;

    @Override
    public Category create(CreateCategory createCategory) {
        CategoryJpaEntity category = categoryPersistenceMapper.mapToJpaEntity(createCategory);
        return categoryPersistenceMapper.mapToDomainEntity(categoryRepository.save(category));
    }

    @Override
    public void update(UpdateCategoryDetails details) {
        categoryPersistenceMapper.applyUpdatedDetails(details);
    }

    @Override
    public boolean check(String categorySlug) {
        return categoryRepository.existsBySlug(categorySlug);
    }

    @Override
    public List<Category> loadList() {
        List<CategoryJpaEntity> categories = categoryRepository.findAll();
        return categoryPersistenceMapper.mapToDomainEntities(categories);
    }

    @Override
    public Category loadDetailsBySlug(String categorySlug) {
        CategoryJpaEntity category = categoryPersistenceResolver.resolve(categorySlug);
        return categoryPersistenceMapper.mapToDomainEntity(category);
    }

}

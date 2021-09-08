package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.in.category.exception.CategoryNotFoundException;
import food.booking.app.business.app.port.out.category.CreateCategory;
import food.booking.app.business.app.port.out.category.UpdateCategoryDetails;
import food.booking.app.business.domain.Category;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Category persistence mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class CategoryPersistenceMapper {

    private final CategoryPersistenceResolver categoryPersistenceResolver;

    /**
     * Map to category jpa entity
     *
     * @param createCategory create category
     * @return category jpa entity
     */
    CategoryJpaEntity mapToJpaEntity(CreateCategory createCategory) {
        return new CategoryJpaEntity(
                createCategory.slug(),
                createCategory.title(),
                createCategory.sortOrder(),
                createCategory.iconUrl());
    }

    /**
     * Map to category domain entity
     *
     * @param entity category jpa entity
     * @return category domain entity
     */
    Category mapToDomainEntity(CategoryJpaEntity entity) {
        var category = new Category();
        category.setSlug(entity.getSlug());
        category.setTitle(entity.getTitle());
        category.setActive(entity.isActive());
        category.setIconUrl(entity.getIconUrl());
        category.setSortOrder(entity.getSortOrder());
        category.setCreatedAt(entity.getCreatedAt());
        category.setUpdatedAt(entity.getUpdatedAt());
        return category;
    }

    /**
     * Map to domain entities
     *
     * @param entities category jpa entities
     * @return list of category domain entities
     */
    List<Category> mapToDomainEntities(List<CategoryJpaEntity> entities) {
        return entities.stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    /**
     * Apply updated details
     *
     * @param details update category details
     * @return updated category details
     * @throws CategoryNotFoundException if category was not found
     */
    Category applyUpdatedDetails(UpdateCategoryDetails details) {
        CategoryJpaEntity entity = categoryPersistenceResolver.resolve(details);
        entity.setTitle(details.title());
        entity.setActive(details.active());
        entity.setIconUrl(details.iconUrl());
        entity.setSortOrder(details.sortOrder());
        return mapToDomainEntity(entity);
    }

}

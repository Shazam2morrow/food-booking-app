package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.out.category.CreateCategory;
import food.booking.app.business.app.port.out.category.UpdateCategoryDetails;
import food.booking.app.business.domain.Category;
import lombok.RequiredArgsConstructor;

/**
 * Category persistence mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class CategoryPersistenceMapper {

    CategoryJpaEntity mapToJpaEntity(CreateCategory createCategory) {
        return new CategoryJpaEntity(
                createCategory.slug(),
                createCategory.title(),
                createCategory.sortOrder(),
                createCategory.iconUrl());
    }

    Category mapToDomainEntity(CategoryJpaEntity entity) {
        var dto = new Category();
        dto.setSlug(entity.getSlug());
        dto.setTitle(entity.getTitle());
        dto.setActive(entity.isActive());
        dto.setIconUrl(entity.getIconUrl());
        dto.setSortOrder(entity.getSortOrder());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    void applyUpdatedDetails(CategoryJpaEntity category, UpdateCategoryDetails details) {
        category.setTitle(details.title());
        category.setActive(details.active());
        category.setIconUrl(details.iconUrl());
        category.setSortOrder(details.sortOrder());
    }

}

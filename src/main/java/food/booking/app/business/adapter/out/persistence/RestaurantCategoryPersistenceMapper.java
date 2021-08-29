package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.domain.Category;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Restaurant category persistence mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class RestaurantCategoryPersistenceMapper {

    private final CategoryPersistenceMapper categoryPersistenceMapper;

    private final CategoryPersistenceResolver categoryPersistenceResolver;

    RestaurantCategoryJpaEntity mapToJpaEntity(Category category) {
        RestaurantCategoryJpaEntity restaurantCategoryJpaEntity = new RestaurantCategoryJpaEntity();
        restaurantCategoryJpaEntity.setCategory(categoryPersistenceResolver.resolve(category));
        return restaurantCategoryJpaEntity;
    }

    RestaurantCategoryJpaEntity mapToJpaEntity(Category category, RestaurantJpaEntity restaurant) {
        RestaurantCategoryJpaEntity restaurantCategoryJpaEntity = new RestaurantCategoryJpaEntity();
        restaurantCategoryJpaEntity.setCategory(categoryPersistenceResolver.resolve(category));
        restaurantCategoryJpaEntity.setRestaurant(restaurant);
        return restaurantCategoryJpaEntity;
    }

    List<RestaurantCategoryJpaEntity> mapToJpaEntities(List<Category> categories, RestaurantJpaEntity restaurant) {
        return Objects.isNull(categories)
                ? null : categories.stream()
                .map(category -> mapToJpaEntity(category, restaurant))
                .collect(Collectors.toList());
    }

    Category mapToDomainEntity(RestaurantCategoryJpaEntity entity) {
        return categoryPersistenceMapper.mapToDomainEntity(entity.getCategory());
    }

}

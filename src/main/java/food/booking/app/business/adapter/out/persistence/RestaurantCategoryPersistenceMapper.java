package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.in.category.exception.CategoryNotFoundException;
import food.booking.app.business.app.port.in.restaurant.exception.RestaurantNotFoundException;
import food.booking.app.business.domain.Category;
import food.booking.app.business.domain.Restaurant;
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

    private final CategoryPersistenceResolver categoryPersistenceResolver;

    private final RestaurantPersistenceResolver restaurantPersistenceResolver;

    /**
     * Map to restaurant category entity
     *
     * @param category   category
     * @param restaurant restaurant
     * @return restaurant category entity
     * @throws CategoryNotFoundException   if category was not found
     * @throws RestaurantNotFoundException if restaurant was not found
     */
    RestaurantCategoryJpaEntity mapToJpaEntity(Category category, Restaurant restaurant) {
        return new RestaurantCategoryJpaEntity(
                categoryPersistenceResolver.resolve(category),
                restaurantPersistenceResolver.resolve(restaurant));
    }

    /**
     * Map to restaurant category entities
     *
     * @param categories categories
     * @param restaurant restaurants
     * @return restaurant category entities
     * @throws CategoryNotFoundException   if category was not found
     * @throws RestaurantNotFoundException if restaurant was not found
     */
    List<RestaurantCategoryJpaEntity> mapToJpaEntities(List<Category> categories, Restaurant restaurant) {
        return Objects.isNull(categories)
                ? null : categories.stream()
                .map(category -> mapToJpaEntity(category, restaurant))
                .collect(Collectors.toList());
    }

}

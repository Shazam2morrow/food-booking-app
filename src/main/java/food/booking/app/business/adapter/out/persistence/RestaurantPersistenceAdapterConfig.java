package food.booking.app.business.adapter.out.persistence;

import food.booking.app.shared.domain.LocationMapper;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * persistence adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class RestaurantPersistenceAdapterConfig {

    @Bean
    RestaurantSlugpersistenceAdapter restaurantpersistenceAdapter(
            RestaurantRepository restaurantRepository,
            RestaurantPersistenceMapper restaurantPersistenceMapper,
            RestaurantScheduleRepository restaurantScheduleRepository,
            RestaurantCategoryRepository restaurantCategoryRepository,
            RestaurantSchedulePersistenceMapper restaurantSchedulePersistenceMapper,
            RestaurantCategoryPersistenceMapper restaurantCategoryPersistenceMapper,
            RestaurantPersistenceResolver restaurantPersistenceResolver) {
        return new RestaurantSlugpersistenceAdapter(
                restaurantRepository,
                restaurantPersistenceMapper,
                restaurantScheduleRepository,
                restaurantCategoryRepository,
                restaurantPersistenceResolver,
                restaurantSchedulePersistenceMapper,
                restaurantCategoryPersistenceMapper
        );
    }

    @Bean
    LocationMapper locationMapper(GeometryFactory geometryFactory) {
        return new LocationMapper(geometryFactory);
    }

    @Bean
    RestaurantPersistenceMapper restaurantpersistenceMapper(LocationMapper locationMapper) {
        return new RestaurantPersistenceMapper(locationMapper);
    }

    @Bean
    RestaurantSchedulePersistenceMapper restaurantSchedulepersistenceMapper() {
        return new RestaurantSchedulePersistenceMapper();
    }

    @Bean
    RestaurantPersistenceResolver restaurantpersistenceResolver(RestaurantRepository restaurantRepository) {
        return new RestaurantPersistenceResolver(restaurantRepository);
    }

    @Bean
    CategoryPersistenceResolver categorypersistenceResolver(CategoryRepository categoryRepository) {
        return new CategoryPersistenceResolver(categoryRepository);
    }

    @Bean
    RestaurantCategoryPersistenceMapper restaurantCategorypersistenceMapper(
            CategoryPersistenceMapper categoryPersistenceMapper,
            CategoryPersistenceResolver categoryPersistenceResolver) {
        return new RestaurantCategoryPersistenceMapper(categoryPersistenceMapper, categoryPersistenceResolver);
    }

}

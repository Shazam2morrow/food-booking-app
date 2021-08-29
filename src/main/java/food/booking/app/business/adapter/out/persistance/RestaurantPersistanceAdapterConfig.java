package food.booking.app.business.adapter.out.persistance;

import food.booking.app.shared.domain.LocationMapper;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Persistance adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class RestaurantPersistanceAdapterConfig {

    @Bean
    RestaurantSlugPersistanceAdapter restaurantPersistanceAdapter(
            RestaurantRepository restaurantRepository,
            RestaurantPersistanceMapper restaurantPersistanceMapper,
            RestaurantScheduleRepository restaurantScheduleRepository,
            RestaurantCategoryRepository restaurantCategoryRepository,
            RestaurantSchedulePersistanceMapper restaurantSchedulePersistanceMapper,
            RestaurantCategoryPersistanceMapper restaurantCategoryPersistanceMapper,
            RestaurantPersistanceResolver restaurantPersistanceResolver) {
        return new RestaurantSlugPersistanceAdapter(
                restaurantRepository,
                restaurantPersistanceMapper,
                restaurantScheduleRepository,
                restaurantCategoryRepository,
                restaurantPersistanceResolver,
                restaurantSchedulePersistanceMapper,
                restaurantCategoryPersistanceMapper
        );
    }

    @Bean
    LocationMapper locationMapper(GeometryFactory geometryFactory) {
        return new LocationMapper(geometryFactory);
    }

    @Bean
    RestaurantPersistanceMapper restaurantPersistanceMapper(LocationMapper locationMapper) {
        return new RestaurantPersistanceMapper(locationMapper);
    }

    @Bean
    RestaurantSchedulePersistanceMapper restaurantSchedulePersistanceMapper() {
        return new RestaurantSchedulePersistanceMapper();
    }

    @Bean
    RestaurantPersistanceResolver restaurantPersistanceResolver(RestaurantRepository restaurantRepository) {
        return new RestaurantPersistanceResolver(restaurantRepository);
    }

    @Bean
    CategoryPersistanceResolver categoryPersistanceResolver(CategoryRepository categoryRepository) {
        return new CategoryPersistanceResolver(categoryRepository);
    }

    @Bean
    RestaurantCategoryPersistanceMapper restaurantCategoryPersistanceMapper(
            CategoryPersistanceMapper categoryPersistanceMapper,
            CategoryPersistanceResolver categoryPersistanceResolver) {
        return new RestaurantCategoryPersistanceMapper(categoryPersistanceMapper, categoryPersistanceResolver);
    }

}

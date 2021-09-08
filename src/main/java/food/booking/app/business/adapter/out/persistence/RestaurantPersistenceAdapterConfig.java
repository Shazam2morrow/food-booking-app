package food.booking.app.business.adapter.out.persistence;

import food.booking.app.shared.domain.mapper.LocationMapper;
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
    RestaurantPersistenceAdapter restaurantPersistenceAdapter(
            RestaurantRepository restaurantRepository,
            CategoryPersistenceMapper categoryPersistenceMapper,
            RestaurantPersistenceMapper restaurantPersistenceMapper,
            RestaurantScheduleRepository restaurantScheduleRepository,
            RestaurantCategoryRepository restaurantCategoryRepository,
            RestaurantSchedulePersistenceMapper restaurantSchedulePersistenceMapper,
            RestaurantCategoryPersistenceMapper restaurantCategoryPersistenceMapper,
            RestaurantPersistenceResolver restaurantPersistenceResolver) {
        return new RestaurantPersistenceAdapter(
                restaurantRepository,
                categoryPersistenceMapper,
                restaurantPersistenceMapper,
                restaurantScheduleRepository,
                restaurantCategoryRepository,
                restaurantPersistenceResolver,
                restaurantSchedulePersistenceMapper,
                restaurantCategoryPersistenceMapper
        );
    }

    @Bean
    RestaurantPersistenceMapper restaurantPersistenceMapper(
            LocationMapper locationMapper,
            RestaurantPersistenceResolver restaurantPersistenceResolver) {
        return new RestaurantPersistenceMapper(locationMapper, restaurantPersistenceResolver);
    }

    @Bean
    RestaurantSchedulePersistenceMapper restaurantSchedulePersistenceMapper(
            RestaurantPersistenceResolver restaurantPersistenceResolver) {
        return new RestaurantSchedulePersistenceMapper(restaurantPersistenceResolver);
    }

    @Bean
    RestaurantPersistenceResolver restaurantPersistenceResolver(RestaurantRepository restaurantRepository) {
        return new RestaurantPersistenceResolver(restaurantRepository);
    }

    @Bean
    RestaurantCategoryPersistenceMapper restaurantCategoryPersistenceMapper(
            CategoryPersistenceResolver categoryPersistenceResolver,
            RestaurantPersistenceResolver restaurantPersistenceResolver) {
        return new RestaurantCategoryPersistenceMapper(categoryPersistenceResolver, restaurantPersistenceResolver);
    }

}

package food.booking.app.business.adapter.out.persistance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Menu persistance adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class MenuPersistanceAdapterConfig {

    @Bean
    MenuSlugPersistanceAdapter menuPersistanceAdapter(
            MenuRepository menuRepository,
            MenuPersistanceMapper menuPersistanceMapper,
            MenuPersistanceResolver menuPersistanceResolver) {
        return new MenuSlugPersistanceAdapter(menuRepository, menuPersistanceMapper, menuPersistanceResolver);
    }

    @Bean
    MenuPersistanceMapper menuPersistanceMapper(RestaurantPersistanceResolver restaurantPersistanceResolver) {
        return new MenuPersistanceMapper(restaurantPersistanceResolver);
    }

    @Bean
    MenuPersistanceResolver menuPersistanceResolver(MenuRepository menuRepository) {
        return new MenuPersistanceResolver(menuRepository);
    }

}

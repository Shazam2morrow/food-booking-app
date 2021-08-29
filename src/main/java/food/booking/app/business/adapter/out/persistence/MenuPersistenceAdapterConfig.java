package food.booking.app.business.adapter.out.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Menu persistence adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class MenuPersistenceAdapterConfig {

    @Bean
    MenuSlugPersistenceAdapter menupersistenceAdapter(
            MenuRepository menuRepository,
            MenuPersistenceMapper menuPersistenceMapper,
            MenuPersistenceResolver menuPersistenceResolver) {
        return new MenuSlugPersistenceAdapter(menuRepository, menuPersistenceMapper, menuPersistenceResolver);
    }

    @Bean
    MenuPersistenceMapper menupersistenceMapper(RestaurantPersistenceResolver restaurantPersistenceResolver) {
        return new MenuPersistenceMapper(restaurantPersistenceResolver);
    }

    @Bean
    MenuPersistenceResolver menupersistenceResolver(MenuRepository menuRepository) {
        return new MenuPersistenceResolver(menuRepository);
    }

}

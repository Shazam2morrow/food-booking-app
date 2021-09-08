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
    MenuPersistenceAdapter menuPersistenceAdapter(
            MenuRepository menuRepository,
            MenuPersistenceMapper menuPersistenceMapper,
            MenuPersistenceResolver menuPersistenceResolver) {
        return new MenuPersistenceAdapter(menuRepository, menuPersistenceMapper, menuPersistenceResolver);
    }

    @Bean
    MenuPersistenceMapper menuPersistenceMapper(MenuPersistenceResolver menuPersistenceResolver,
                                                RestaurantPersistenceResolver restaurantPersistenceResolver) {
        return new MenuPersistenceMapper(menuPersistenceResolver, restaurantPersistenceResolver);
    }

    @Bean
    MenuPersistenceResolver menuPersistenceResolver(MenuRepository menuRepository) {
        return new MenuPersistenceResolver(menuRepository);
    }

}

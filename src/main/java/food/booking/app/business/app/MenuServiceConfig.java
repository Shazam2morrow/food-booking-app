package food.booking.app.business.app;

import food.booking.app.business.app.port.out.menu.*;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Menu service configuration
 *
 * @author shazam2morrow
 */
@Configuration
class MenuServiceConfig {

    @Bean
    MenuService menuService(CreateMenuPort createMenuPort,
                            MenuServiceMapper menuServiceMapper,
                            LoadMenuDetailsPort loadMenuDetailsPort,
                            UpdateMenuDetailsPort updateMenuDetailsPort,
                            LoadRestaurantMenuListPort loadRestaurantMenuListPort) {
        return new MenuService(
                createMenuPort,
                menuServiceMapper,
                loadMenuDetailsPort,
                updateMenuDetailsPort,
                loadRestaurantMenuListPort);
    }

    @Bean
    MenuServiceMapper menuServiceMapper(CheckMenuSlugPort checkMenuSlugPort,
                                        RandomStringGenerator randomStringGenerator) {
        return new MenuServiceMapper(checkMenuSlugPort, randomStringGenerator);
    }

}

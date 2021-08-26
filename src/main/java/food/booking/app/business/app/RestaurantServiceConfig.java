package food.booking.app.business.app;

import food.booking.app.business.app.port.out.restaurant.*;
import food.booking.app.storage.app.FileUriResolver;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application configuration
 *
 * @author shazam2morrow
 */
@Configuration
class RestaurantServiceConfig {

    @Bean
    RestaurantService restaurantService(CreateRestaurantPort createRestaurantPort,
                                        LoadRestaurantDetailsPort loadRestaurantDetailsPort,
                                        UpdateRestaurantDetailsPort updateRestaurantDetailsPort,
                                        RestaurantServiceMapper restaurantServiceMapper,
                                        LoadRestaurantSlicePort loadRestaurantSlicePort) {
        return new RestaurantService(
                createRestaurantPort,
                loadRestaurantSlicePort,
                restaurantServiceMapper,
                loadRestaurantDetailsPort,
                updateRestaurantDetailsPort);
    }

    @Bean
    RestaurantServiceMapper restaurantAppMapper(FileUriResolver fileUriResolver,
                                                CheckRestaurantSlugPort checkRestaurantSlugPort,
                                                RandomStringGenerator randomStringGenerator) {
        return new RestaurantServiceMapper(fileUriResolver, checkRestaurantSlugPort, randomStringGenerator);
    }

}

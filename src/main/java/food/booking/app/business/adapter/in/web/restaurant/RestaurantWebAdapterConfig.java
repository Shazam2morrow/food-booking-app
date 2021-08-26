package food.booking.app.business.adapter.in.web.restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import food.booking.app.business.adapter.in.web.category.CategoryModelAssembler;
import food.booking.app.business.adapter.in.web.menu.MenuModelAssembler;
import food.booking.app.business.app.port.in.restaurant.CreateRestaurantUseCase;
import food.booking.app.business.app.port.in.restaurant.LoadRestaurantDetailsUseCase;
import food.booking.app.business.app.port.in.restaurant.LoadRestaurantSliceUseCase;
import food.booking.app.business.app.port.in.restaurant.UpdateRestaurantDetailsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Restaurant web adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class RestaurantWebAdapterConfig {

    @Bean
    CreateRestaurantController createRestaurantController(RestaurantWebMapper restaurantWebMapper,
                                                          CreateRestaurantUseCase createRestaurantUseCase,
                                                          RestaurantModelAssembler restaurantModelAssembler) {
        return new CreateRestaurantController(restaurantWebMapper, createRestaurantUseCase, restaurantModelAssembler);
    }

    @Bean
    UpdateRestaurantDetailsController updateRestaurantDetailsController(
            ObjectMapper objectMapper,
            LoadRestaurantDetailsUseCase restaurantDetailsUseCase,
            UpdateRestaurantDetailsUseCase updateRestaurantDetailsUseCase) {
        return new UpdateRestaurantDetailsController(
                objectMapper,
                restaurantDetailsUseCase,
                updateRestaurantDetailsUseCase);
    }

    @Bean
    LoadRestaurantDetailsController loadRestaurantDetailsController(
            RestaurantModelAssembler restaurantModelAssembler,
            LoadRestaurantDetailsUseCase loadRestaurantDetailsUseCase) {
        return new LoadRestaurantDetailsController(restaurantModelAssembler, loadRestaurantDetailsUseCase);
    }

    @Bean
    LoadRestaurantsController loadRestaurantsController(
            LoadRestaurantSliceUseCase loadRestaurantSliceUseCase,
            RestaurantPreviewModelAssembler restaurantPreviewModelAssembler) {
        return new LoadRestaurantsController(loadRestaurantSliceUseCase, restaurantPreviewModelAssembler);
    }

    @Bean
    RestaurantWebMapper restaurantWebMapper() {
        return new RestaurantWebMapper();
    }

    @Bean
    RestaurantModelAssembler restaurantModelAssembler(MenuModelAssembler menuModelAssembler,
                                                      CategoryModelAssembler categoryModelAssembler) {
        return new RestaurantModelAssembler(
                LoadRestaurantDetailsController.class,
                RestaurantModel.class,
                menuModelAssembler,
                categoryModelAssembler);
    }

    @Bean
    RestaurantPreviewModelAssembler restaurantPreviewModelAssembler() {
        return new RestaurantPreviewModelAssembler(LoadRestaurantDetailsController.class, RestaurantPreviewModel.class);
    }

}

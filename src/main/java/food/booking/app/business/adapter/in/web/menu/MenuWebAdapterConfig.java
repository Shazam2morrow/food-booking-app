package food.booking.app.business.adapter.in.web.menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import food.booking.app.business.adapter.in.web.group.GroupModelAssembler;
import food.booking.app.business.adapter.in.web.restaurant.RestaurantPreviewModelAssembler;
import food.booking.app.business.app.port.in.menu.*;
import food.booking.app.business.app.port.in.restaurant.LoadRestaurantDetailsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Menu web adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class MenuWebAdapterConfig {

    @Bean
    CreateMenuController createMenuController(MenuWebMapper menuWebMapper,
                                              CreateMenuUseCase createMenuUseCase,
                                              MenuModelAssembler menuModelAssembler,
                                              RestaurantPreviewModelAssembler restaurantPreviewModelAssembler) {
        return new CreateMenuController(
                menuWebMapper,
                createMenuUseCase,
                menuModelAssembler,
                restaurantPreviewModelAssembler);
    }

    @Bean
    LoadMenusController loadMenusController(MenuModelAssembler menuModelAssembler,
                                            LoadRestaurantDetailsUseCase loadRestaurantDetailsUseCase,
                                            LoadRestaurantMenuListUseCase loadRestaurantMenuListUseCase,
                                            RestaurantPreviewModelAssembler restaurantPreviewModelAssembler) {
        return new LoadMenusController(
                menuModelAssembler,
                loadRestaurantDetailsUseCase,
                loadRestaurantMenuListUseCase,
                restaurantPreviewModelAssembler);
    }

    @Bean
    LoadMenuDetailsController loadMenuDetailsController(LoadMenuDetailsUseCase loadMenuDetailsUseCase,
                                                        MenuModelAssembler menuModelAssembler) {
        return new LoadMenuDetailsController(menuModelAssembler, loadMenuDetailsUseCase);
    }

    @Bean
    UpdateMenuDetailsController updateMenuDetailsController(
            ObjectMapper objectMapper,
            LoadMenuDetailsUseCase loadMenuDetailsUseCase,
            UpdateMenuDetailsUseCase updateMenuDetailsUseCase
    ) {
        return new UpdateMenuDetailsController(
                objectMapper,
                loadMenuDetailsUseCase,
                updateMenuDetailsUseCase);
    }

    @Bean
    DeleteMenuController deleteMenuController(DeleteMenuUseCase deleteMenuUseCase) {
        return new DeleteMenuController(deleteMenuUseCase);
    }

    @Bean
    MenuWebMapper menuWebMapper() {
        return new MenuWebMapper();
    }

    @Bean
    MenuModelAssembler menuModelAssembler(GroupModelAssembler groupModelAssembler) {
        return new MenuModelAssembler(LoadMenuDetailsController.class, MenuModel.class, groupModelAssembler);
    }

}

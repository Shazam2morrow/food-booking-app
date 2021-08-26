package food.booking.app.business.adapter.in.web.menu;

import food.booking.app.business.adapter.in.web.restaurant.RestaurantPreviewModel;
import food.booking.app.business.adapter.in.web.restaurant.RestaurantPreviewModelAssembler;
import food.booking.app.business.app.port.in.menu.LoadRestaurantMenuListUseCase;
import food.booking.app.business.app.port.in.restaurant.LoadRestaurantDetailsUseCase;
import food.booking.app.business.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Load menus controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
class LoadMenusController {

    private final MenuModelAssembler menuModelAssembler;

    private final LoadRestaurantDetailsUseCase loadRestaurantDetailsUseCase;

    private final LoadRestaurantMenuListUseCase loadRestaurantMenuListUseCase;

    private final RestaurantPreviewModelAssembler restaurantPreviewModelAssembler;

    /**
     * Load menus
     *
     * @param restaurantSlug restaurant slug
     * @return menu model
     */
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE})
    ResponseEntity<RepresentationModel<?>> loadMenus(
            @RequestParam(name = "restaurant") String restaurantSlug) {
        log.debug("REST request to load list of restaurant menus: {}", restaurantSlug);
        RestaurantPreviewModel restaurant = fetchRestaurant(restaurantSlug);
        List<MenuModel> menus = fetchRestaurantMenus(restaurantSlug);
        return ResponseEntity.ok(buildHalModel(restaurant, menus));
    }

    /**
     * Build HAL model
     *
     * @param restaurant restaurant preview model
     * @param menus      menu model
     * @return HAL representation model
     */
    private RepresentationModel<?> buildHalModel(RestaurantPreviewModel restaurant, List<MenuModel> menus) {
        HalModelBuilder builder = HalModelBuilder.emptyHalModel();
        if (menus.isEmpty()) {
            builder = builder.embed(Collections.emptyList(), MenuModel.class);
        }
        return builder.embed(menus)
                .preview(restaurant)
                .forLink(restaurant.getRequiredLink("self").withRel("restaurantPreview"))
                .link(buildSelfLink(restaurant.getSlug()))
                .build();
    }

    /**
     * Fetch restaurant
     *
     * @param restaurantSlug restaurant slug
     * @return restaurant preview model
     */
    private RestaurantPreviewModel fetchRestaurant(String restaurantSlug) {
        Restaurant restaurant = loadRestaurantDetailsUseCase.loadDetailsBySlug(restaurantSlug);
        return restaurantPreviewModelAssembler.toModel(restaurant);
    }

    /**
     * Fetch restaurant menus
     *
     * @param restaurantSlug restaurant slug
     * @return list of restaurant menus
     */
    private List<MenuModel> fetchRestaurantMenus(String restaurantSlug) {
        return loadRestaurantMenuListUseCase.loadRestaurantMenuList(restaurantSlug)
                .stream()
                .map(menuModelAssembler::toModel)
                .collect(Collectors.toList());
    }

    /**
     * Build self link
     *
     * @param restaurantSlug restaurant slug
     * @return self link
     */
    private Link buildSelfLink(String restaurantSlug) {
        return linkTo(methodOn(getClass()).loadMenus(restaurantSlug)).withSelfRel();
    }

}

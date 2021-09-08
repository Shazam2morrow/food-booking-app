package food.booking.app.business.adapter.in.web.restaurant;

import food.booking.app.business.app.port.in.restaurant.LoadRestaurantSliceUseCase;
import food.booking.app.business.domain.Restaurant;
import food.booking.app.shared.web.model.SlicedModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Load restaurants controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
class LoadRestaurantsController {

    private final LoadRestaurantSliceUseCase loadRestaurantSliceUseCase;

    private final RestaurantPreviewModelAssembler restaurantPreviewModelAssembler;

    /**
     * Load restaurant previews
     *
     * @param page page
     * @return restaurant previews model
     */
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE})
    ResponseEntity<SlicedModel<RestaurantPreviewModel>> loadRestaurants(Pageable page) {
        log.debug("REST request to load restaurants: {}", page);
        Slice<Restaurant> restaurants = loadRestaurantSliceUseCase.loadSlice(page);
        return ResponseEntity.ok(restaurantPreviewModelAssembler.toSlicedModel(restaurants));
    }

}

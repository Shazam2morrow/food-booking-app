package food.booking.app.business.adapter.in.web.restaurant;

import food.booking.app.business.app.port.in.restaurant.LoadRestaurantDetailsUseCase;
import food.booking.app.business.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Load restaurant rest controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
class LoadRestaurantDetailsController {

    private final RestaurantModelAssembler restaurantModelAssembler;

    private final LoadRestaurantDetailsUseCase loadRestaurantDetailsUseCase;

    /**
     * Load restaurant details
     *
     * @param restaurantSlug restaurant slug
     * @return restaurant details
     */
    @GetMapping("/{restaurantSlug}")
    ResponseEntity<RepresentationModel<?>> loadRestaurantDetails(@PathVariable String restaurantSlug) {
        log.debug("REST request to load restaurant details: {}", restaurantSlug);
        Restaurant restaurant = loadRestaurantDetailsUseCase.loadDetailsBySlug(restaurantSlug);
        RepresentationModel<?> model = restaurantModelAssembler.toHalModel(restaurant);
        return ResponseEntity.ok(model);
    }

}

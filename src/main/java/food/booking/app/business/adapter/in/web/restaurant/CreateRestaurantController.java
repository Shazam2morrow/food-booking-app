package food.booking.app.business.adapter.in.web.restaurant;

import food.booking.app.business.app.port.in.restaurant.CreateRestaurantCommand;
import food.booking.app.business.app.port.in.restaurant.CreateRestaurantUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Create restaurant controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
class CreateRestaurantController {

    private final RestaurantWebMapper restaurantWebMapper;

    private final CreateRestaurantUseCase createRestaurantUseCase;

    private final RestaurantModelAssembler restaurantModelAssembler;

    /**
     * Create restaurant
     *
     * @param input create restaurant model
     * @return restaurant model
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaTypes.HAL_JSON_VALUE})
    ResponseEntity<RepresentationModel<?>> createRestaurant(@Valid @RequestBody Restaurant input) {
        log.debug("REST request to create restaurant: {}", input);
        CreateRestaurantCommand command = restaurantWebMapper.mapToCommand(input);
        var restaurant = createRestaurantUseCase.create(command);
        RepresentationModel<?> model = restaurantModelAssembler.toHalModel(restaurant);
        return model.getLink("self")
                .<ResponseEntity<RepresentationModel<?>>>map(link -> ResponseEntity.created(link.toUri()).body(model))
                .orElseGet(() -> new ResponseEntity<>(model, HttpStatus.CREATED));
    }

}

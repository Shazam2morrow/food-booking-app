package food.booking.app.business.adapter.in.web.restaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import food.booking.app.business.app.port.in.restaurant.LoadRestaurantDetailsUseCase;
import food.booking.app.business.app.port.in.restaurant.UpdateRestaurantDetailsCommand;
import food.booking.app.business.app.port.in.restaurant.UpdateRestaurantDetailsUseCase;
import food.booking.app.business.domain.Restaurant;
import food.booking.app.shared.PatchApplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Update restaurant details controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequestMapping("/restaurants")
class UpdateRestaurantDetailsController extends PatchApplier<Restaurant, UpdateRestaurantDetailsCommand> {

    private final LoadRestaurantDetailsUseCase loadRestaurantDetailsUseCase;

    private final UpdateRestaurantDetailsUseCase updateRestaurantDetailsUseCase;

    UpdateRestaurantDetailsController(ObjectMapper objectMapper,
                                      LoadRestaurantDetailsUseCase loadRestaurantDetailsUseCase,
                                      UpdateRestaurantDetailsUseCase updateRestaurantDetailsUseCase) {
        super(objectMapper);
        this.loadRestaurantDetailsUseCase = loadRestaurantDetailsUseCase;
        this.updateRestaurantDetailsUseCase = updateRestaurantDetailsUseCase;
    }

    /**
     * Update restaurant details
     *
     * @param restaurantSlug restaurant slug
     * @param jsonPatch      json patch
     * @return no content
     * @throws JsonPatchException      if patch can not be applied
     * @throws JsonProcessingException if json could not be processed
     */
    @PatchMapping(path = "/{restaurantSlug}", consumes = {PATCH_TYPE})
    ResponseEntity<Void> updateRestaurantDetails(
            @PathVariable String restaurantSlug,
            @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        log.debug("REST request to update restaurant details: {}, {}", restaurantSlug, jsonPatch);
        Restaurant details = loadRestaurantDetailsUseCase.loadDetailsBySlug(restaurantSlug);
        updateRestaurantDetailsUseCase.update(applyPatch(jsonPatch, details));
        return ResponseEntity.noContent().build();
    }

    @Override
    protected Class<UpdateRestaurantDetailsCommand> getOutputType() {
        return UpdateRestaurantDetailsCommand.class;
    }

}

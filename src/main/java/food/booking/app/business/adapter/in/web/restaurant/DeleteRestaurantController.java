package food.booking.app.business.adapter.in.web.restaurant;

import food.booking.app.business.app.port.in.restaurant.DeleteRestaurantUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Delete restaurant controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
class DeleteRestaurantController {

    private final DeleteRestaurantUseCase deleteRestaurantUseCase;

    /**
     * Delete restaurant
     *
     * @param restarauntSlug restaurant slug
     */
    @DeleteMapping("/{restarauntSlug}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteRestaurant(@PathVariable String restarauntSlug) {
        log.debug("REST request to delete restaraunt: {}", restarauntSlug);
        deleteRestaurantUseCase.deleteBySlug(restarauntSlug);
    }

}

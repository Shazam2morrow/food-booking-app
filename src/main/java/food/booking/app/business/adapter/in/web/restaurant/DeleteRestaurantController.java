package food.booking.app.business.adapter.in.web.restaurant;

import food.booking.app.business.app.port.in.restaurant.DeleteRestaurantUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @return no content
     */
    @DeleteMapping("/{restarauntSlug}")
    ResponseEntity<Void> deleteRestaurant(@PathVariable String restarauntSlug) {
        log.debug("REST request to delete restaraunt: {}", restarauntSlug);
        deleteRestaurantUseCase.deleteBySlug(restarauntSlug);
        return ResponseEntity.noContent().build();
    }

}

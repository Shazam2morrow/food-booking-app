package food.booking.app.business.adapter.in.web.item;

import food.booking.app.business.app.port.in.item.DeleteItemUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Delete item controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
class DeleteItemController {

    private final DeleteItemUseCase deleteItemUseCase;

    /**
     * Delete item
     *
     * @param itemSlug item slug
     * @return no content
     */
    @DeleteMapping(path = "/{itemSlug}")
    ResponseEntity<Void> deleteItem(@PathVariable String itemSlug) {
        log.debug("REST request to delete item: {}", itemSlug);
        deleteItemUseCase.deleteBySlug(itemSlug);
        return ResponseEntity.noContent().build();
    }

}

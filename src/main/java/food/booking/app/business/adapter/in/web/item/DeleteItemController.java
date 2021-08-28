package food.booking.app.business.adapter.in.web.item;

import food.booking.app.business.app.port.in.item.DeleteItemUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
     */
    @DeleteMapping(path = "/{itemSlug}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteItem(@PathVariable String itemSlug) {
        log.debug("REST request to delete item: {}", itemSlug);
        deleteItemUseCase.deleteBySlug(itemSlug);
    }

}

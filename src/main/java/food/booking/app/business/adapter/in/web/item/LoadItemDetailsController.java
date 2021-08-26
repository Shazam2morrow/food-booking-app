package food.booking.app.business.adapter.in.web.item;

import food.booking.app.business.app.port.in.item.LoadItemDetailsUseCase;
import food.booking.app.business.domain.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Load item details controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
class LoadItemDetailsController {

    private final ItemModelAssembler itemModelAssembler;

    private final LoadItemDetailsUseCase loadItemDetailsUseCase;

    /**
     * Load item details
     *
     * @param itemSlug item slug
     * @return item model
     */
    @GetMapping(path = "/{itemSlug}", produces = {MediaTypes.HAL_JSON_VALUE})
    ResponseEntity<ItemModel> loadItemDetails(@PathVariable String itemSlug) {
        log.debug("REST request to load item details: {}", itemSlug);
        Item item = loadItemDetailsUseCase.loadDetailsBySlug(itemSlug);
        return ResponseEntity.ok(itemModelAssembler.toModel(item));
    }

}

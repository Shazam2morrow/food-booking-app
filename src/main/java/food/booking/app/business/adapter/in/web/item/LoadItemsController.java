package food.booking.app.business.adapter.in.web.item;

import food.booking.app.business.app.port.in.group.LoadGroupItemSliceUseCase;
import food.booking.app.business.domain.Item;
import food.booking.app.shared.web.model.SlicedModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Load items controller
 *
 * @author shaza2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
class LoadItemsController {

    private final ItemModelAssembler itemModelAssembler;

    private final LoadGroupItemSliceUseCase loadGroupItemSliceUseCase;

    /**
     * Load items
     *
     * @param groupSlug group slug
     * @param page      page
     * @return sliced item model
     */
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE})
    ResponseEntity<SlicedModel<ItemModel>> loadItems(
            @RequestParam(name = "group") String groupSlug, Pageable page) {
        log.debug("REST request to load items: {}", groupSlug);
        Slice<Item> items = loadGroupItemSliceUseCase.loadSliceByGroupSlug(groupSlug, page);
        SlicedModel<ItemModel> model = itemModelAssembler.toSlicedModel(items, groupSlug);
        return ResponseEntity.ok(model);
    }

}

package food.booking.app.business.adapter.in.web.item;

import food.booking.app.business.adapter.in.web.group.GroupModel;
import food.booking.app.business.adapter.in.web.group.GroupModelAssembler;
import food.booking.app.business.app.port.in.item.CreateItemCommand;
import food.booking.app.business.app.port.in.item.CreateItemUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Create item controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
class CreateItemController {

    private final ItemWebMapper itemWebMapper;

    private final CreateItemUseCase createItemUseCase;

    private final ItemModelAssembler itemModelAssembler;

    private final GroupModelAssembler groupModelAssembler;

    private final static String SELF = "self";

    /**
     * Create item
     *
     * @param input create item model
     * @return item model
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaTypes.HAL_JSON_VALUE})
    ResponseEntity<RepresentationModel<?>> createItem(@Valid @RequestBody Item input) {
        log.debug("REST request to create item: {}", input);
        ItemModel item = executeCreateItem(input);
        Link self = item.getRequiredLink(SELF);
        item.removeLinks();
        return ResponseEntity.created(self.toUri())
                .body(buildHalModel(item, groupModelAssembler.toModel(input.group()), self));
    }

    /**
     * Execute create item
     *
     * @param input create item model
     * @return item model
     */
    private ItemModel executeCreateItem(Item input) {
        CreateItemCommand command = itemWebMapper.mapToCommand(input);
        food.booking.app.business.domain.Item item = createItemUseCase.create(command);
        return itemModelAssembler.toModel(item);
    }

    /**
     * Build HAL model
     *
     * @param item  item model
     * @param group group model
     * @param self  self link
     * @return HAL representation model
     */
    private RepresentationModel<?> buildHalModel(ItemModel item, GroupModel group, Link self) {
        return HalModelBuilder.halModelOf(item)
                .preview(group)
                .forLink(group.getRequiredLink(SELF).withRel("group"))
                .link(self)
                .build();
    }

}

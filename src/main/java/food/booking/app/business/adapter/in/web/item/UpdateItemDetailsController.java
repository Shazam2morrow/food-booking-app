package food.booking.app.business.adapter.in.web.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import food.booking.app.business.app.port.in.item.LoadItemDetailsUseCase;
import food.booking.app.business.app.port.in.item.UpdateItemDetailsCommand;
import food.booking.app.business.app.port.in.item.UpdateItemDetailsUseCase;
import food.booking.app.business.domain.Item;
import food.booking.app.shared.PatchApplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Update item details controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequestMapping("/items")
class UpdateItemDetailsController extends PatchApplier<Item, UpdateItemDetailsCommand> {

    private final LoadItemDetailsUseCase loadItemDetailsUseCase;

    private final UpdateItemDetailsUseCase updateItemDetailsUseCase;

    public UpdateItemDetailsController(ObjectMapper objectMapper,
                                       LoadItemDetailsUseCase loadItemDetailsUseCase,
                                       UpdateItemDetailsUseCase updateItemDetailsUseCase) {
        super(objectMapper);
        this.loadItemDetailsUseCase = loadItemDetailsUseCase;
        this.updateItemDetailsUseCase = updateItemDetailsUseCase;
    }

    /**
     * Update item details
     *
     * @param itemSlug  item slug
     * @param jsonPatch json patch
     * @throws JsonPatchException      if patch can not be applied
     * @throws JsonProcessingException if patch can not be processed
     */
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PatchMapping(path = "/{itemSlug}", consumes = {PATCH_TYPE})
    void updateItemDetails(
            @PathVariable String itemSlug,
            @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        log.debug("REST request to update item details: {}, {}", itemSlug, jsonPatch);
        Item item = loadItemDetailsUseCase.loadDetailsBySlug(itemSlug);
        updateItemDetailsUseCase.update(applyPatch(jsonPatch, item));
    }

    @Override
    protected Class<UpdateItemDetailsCommand> getOutputType() {
        return UpdateItemDetailsCommand.class;
    }

}

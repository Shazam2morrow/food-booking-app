package food.booking.app.business.adapter.in.web.menu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import food.booking.app.business.app.port.in.menu.LoadMenuDetailsUseCase;
import food.booking.app.business.app.port.in.menu.UpdateMenuDetailsCommand;
import food.booking.app.business.app.port.in.menu.UpdateMenuDetailsUseCase;
import food.booking.app.business.domain.Menu;
import food.booking.app.shared.PatchApplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Update menu details controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequestMapping("/menus")
class UpdateMenuDetailsController extends PatchApplier<Menu, UpdateMenuDetailsCommand> {

    private final LoadMenuDetailsUseCase loadMenuDetailsUseCase;

    private final UpdateMenuDetailsUseCase updateMenuDetailsUseCase;

    public UpdateMenuDetailsController(ObjectMapper objectMapper,
                                       LoadMenuDetailsUseCase loadMenuDetailsUseCase,
                                       UpdateMenuDetailsUseCase updateMenuDetailsUseCase) {
        super(objectMapper);
        this.loadMenuDetailsUseCase = loadMenuDetailsUseCase;
        this.updateMenuDetailsUseCase = updateMenuDetailsUseCase;
    }

    /**
     * Update menu details
     *
     * @param menuSlug menu slug
     * @param patch    json patch
     * @return no content
     * @throws JsonPatchException      if patch can not be applied
     * @throws JsonProcessingException if json could not be processed
     */
    @PatchMapping(path = "/{menuSlug}", consumes = {PATCH_TYPE})
    ResponseEntity<Void> updateMenuDetails(
            @PathVariable String menuSlug,
            @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        log.debug("REST request to update menu details: {}, {}", menuSlug, patch);
        Menu menu = loadMenuDetailsUseCase.loadDetailsBySlug(menuSlug);
        updateMenuDetailsUseCase.update(applyPatch(patch, menu));
        return ResponseEntity.noContent().build();
    }

    @Override
    protected Class<UpdateMenuDetailsCommand> getOutputType() {
        return UpdateMenuDetailsCommand.class;
    }

}

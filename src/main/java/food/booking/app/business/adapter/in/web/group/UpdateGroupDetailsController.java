package food.booking.app.business.adapter.in.web.group;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import food.booking.app.business.app.port.in.group.LoadGroupDetailsUseCase;
import food.booking.app.business.app.port.in.group.UpdateGroupDetailsCommand;
import food.booking.app.business.app.port.in.group.UpdateGroupDetailsUseCase;
import food.booking.app.business.domain.Group;
import food.booking.app.shared.PatchApplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Update group details controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequestMapping("/groups")
class UpdateGroupDetailsController extends PatchApplier<Group, UpdateGroupDetailsCommand> {

    private final LoadGroupDetailsUseCase loadGroupDetailsUseCase;

    private final UpdateGroupDetailsUseCase updateGroupDetailsUseCase;

    public UpdateGroupDetailsController(ObjectMapper objectMapper,
                                        LoadGroupDetailsUseCase loadGroupDetailsUseCase,
                                        UpdateGroupDetailsUseCase updateGroupDetailsUseCase) {
        super(objectMapper);
        this.loadGroupDetailsUseCase = loadGroupDetailsUseCase;
        this.updateGroupDetailsUseCase = updateGroupDetailsUseCase;
    }

    /**
     * Update group details
     *
     * @return no content
     * @throws JsonPatchException      if patch can not be applied
     * @throws JsonProcessingException if json could not be processed
     */
    @PatchMapping(path = "/{groupSlug}", consumes = {PATCH_TYPE})
    ResponseEntity<Void> updateGroupDetails(
            @PathVariable String groupSlug,
            @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        log.debug("REST request to update group details: {}, {}", groupSlug, jsonPatch);
        Group group = loadGroupDetailsUseCase.loadDetailsBySlug(groupSlug);
        updateGroupDetailsUseCase.update(applyPatch(jsonPatch, group));
        return ResponseEntity.noContent().build();
    }

    @Override
    protected Class<UpdateGroupDetailsCommand> getOutputType() {
        return UpdateGroupDetailsCommand.class;
    }

}

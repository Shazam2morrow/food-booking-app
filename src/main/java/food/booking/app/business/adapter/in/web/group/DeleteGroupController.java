package food.booking.app.business.adapter.in.web.group;

import food.booking.app.business.app.port.in.group.DeleteGroupUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Delete group controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
class DeleteGroupController {

    private final DeleteGroupUseCase deleteGroupUseCase;

    /**
     * Delete group
     *
     * @param groupSlug group slug
     */
    @DeleteMapping(path = "/{groupSlug}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteGroup(@PathVariable String groupSlug) {
        log.debug("REST request to delete group: {}", groupSlug);
        deleteGroupUseCase.deleteBySlug(groupSlug);
    }

}

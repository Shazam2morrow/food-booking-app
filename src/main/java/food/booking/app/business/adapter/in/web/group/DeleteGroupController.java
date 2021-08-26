package food.booking.app.business.adapter.in.web.group;

import food.booking.app.business.app.port.in.group.DeleteGroupUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @return no content
     */
    @DeleteMapping(path = "/{groupSlug}")
    ResponseEntity<Void> deleteGroup(@PathVariable String groupSlug) {
        log.debug("REST request to delete group: {}", groupSlug);
        deleteGroupUseCase.deleteBySlug(groupSlug);
        return ResponseEntity.noContent().build();
    }

}

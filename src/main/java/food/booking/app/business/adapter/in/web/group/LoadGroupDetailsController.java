package food.booking.app.business.adapter.in.web.group;

import food.booking.app.business.app.port.in.group.LoadGroupDetailsUseCase;
import food.booking.app.business.domain.Group;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Load group details controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
class LoadGroupDetailsController {

    private final GroupModelAssembler groupModelAssembler;

    private final LoadGroupDetailsUseCase loadGroupDetailsUseCase;

    /**
     * Load group details
     *
     * @param groupSlug group slug
     * @return group model
     */
    @GetMapping(path = "/{groupSlug}")
    ResponseEntity<GroupModel> loadGroupDetails(@PathVariable String groupSlug) {
        log.debug("REST request to load group details: {}", groupSlug);
        Group group = loadGroupDetailsUseCase.loadDetailsBySlug(groupSlug);
        return ResponseEntity.ok(groupModelAssembler.toModel(group));
    }

}

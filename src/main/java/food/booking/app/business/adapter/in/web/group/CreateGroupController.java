package food.booking.app.business.adapter.in.web.group;

import food.booking.app.business.adapter.in.web.menu.MenuModel;
import food.booking.app.business.adapter.in.web.menu.MenuModelAssembler;
import food.booking.app.business.app.port.in.group.CreateGroupCommand;
import food.booking.app.business.app.port.in.group.CreateGroupUseCase;
import food.booking.app.business.domain.Group;
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
 * Create group controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
class CreateGroupController {

    private final GroupWebMapper groupWebMapper;

    private final CreateGroupUseCase createGroupUseCase;

    private final MenuModelAssembler menuModelAssembler;

    private final GroupModelAssembler groupModelAssembler;

    private final static String SELF = "self";

    /**
     * Create group
     *
     * @param input create group model
     * @return group model
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaTypes.HAL_JSON_VALUE})
    ResponseEntity<RepresentationModel<?>> createGroup(@Valid @RequestBody CreateGroupModel input) {
        log.debug("REST request to create group: {}", input);
        GroupModel group = executeCreateGroup(input);
        Link self = group.getRequiredLink(SELF);
        group.removeLinks();
        return ResponseEntity.created(self.toUri())
                .body(buildHalModel(group, menuModelAssembler.toModel(input.menu()), self));
    }

    /**
     * Execute create group
     *
     * @param input create group model
     * @return group model
     */
    private GroupModel executeCreateGroup(CreateGroupModel input) {
        CreateGroupCommand command = groupWebMapper.mapToCommand(input);
        Group group = createGroupUseCase.create(command);
        return groupModelAssembler.toModel(group);
    }

    /**
     * Build HAL model
     *
     * @param group group model
     * @param menu  menu model
     * @param self  self link
     * @return HAL representation model
     */
    private RepresentationModel<?> buildHalModel(GroupModel group, MenuModel menu, Link self) {
        return HalModelBuilder.halModelOf(group)
                .preview(menu)
                .forLink(menu.getRequiredLink(SELF).withRel("menu"))
                .link(self)
                .build();
    }

}

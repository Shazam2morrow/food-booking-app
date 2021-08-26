package food.booking.app.business.adapter.in.web.group;

import food.booking.app.business.adapter.in.web.menu.MenuModel;
import food.booking.app.business.adapter.in.web.menu.MenuModelAssembler;
import food.booking.app.business.app.port.in.group.LoadMenuGroupListUseCase;
import food.booking.app.business.app.port.in.menu.LoadMenuDetailsUseCase;
import food.booking.app.business.domain.Menu;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Load groups controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
class LoadGroupsController {

    private final MenuModelAssembler menuModelAssembler;

    private final GroupModelAssembler groupModelAssembler;

    private final LoadMenuDetailsUseCase loadMenuDetailsUseCase;

    private final LoadMenuGroupListUseCase loadMenuGroupListUseCase;

    /**
     * Load groups
     *
     * @param menuSlug menu slug
     * @return groups model
     */
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE})
    ResponseEntity<RepresentationModel<?>> loadGroups(@RequestParam(name = "menu") String menuSlug) {
        log.debug("REST request to load groups: {}", menuSlug);
        MenuModel menu = fetchMenu(menuSlug);
        List<GroupModel> groups = fetchMenuGroups(menuSlug);
        return ResponseEntity.ok(buildHalModel(menu, groups));
    }

    /**
     * Build HAL model
     *
     * @param menu   menu model
     * @param groups groups model
     * @return groups HAL model
     */
    private RepresentationModel<?> buildHalModel(MenuModel menu, List<GroupModel> groups) {
        HalModelBuilder builder = HalModelBuilder.emptyHalModel();
        if (groups.isEmpty()) {
            builder = builder.embed(Collections.emptyList(), MenuModel.class);
        }
        return builder.embed(groups)
                .preview(menu)
                .forLink(menu.getRequiredLink("self").withRel("menu"))
                .link(buildSelfLink(menu.getSlug()))
                .build();
    }

    /**
     * Fetch menu
     *
     * @param menuSlug menu slug
     * @return menu model
     */
    private MenuModel fetchMenu(String menuSlug) {
        Menu menu = loadMenuDetailsUseCase.loadDetailsBySlug(menuSlug);
        return menuModelAssembler.toModel(menu);
    }

    /**
     * Fetch menu groups
     *
     * @param menuSlug menu slug
     * @return group models
     */
    private List<GroupModel> fetchMenuGroups(String menuSlug) {
        return loadMenuGroupListUseCase.loadListByMenuSlug(menuSlug)
                .stream()
                .map(groupModelAssembler::toModel)
                .collect(Collectors.toList());
    }

    /**
     * Build self link
     *
     * @param menuSlug menu slug
     * @return self link
     */
    private Link buildSelfLink(String menuSlug) {
        return linkTo(methodOn(getClass()).loadGroups(menuSlug)).withSelfRel();
    }

}

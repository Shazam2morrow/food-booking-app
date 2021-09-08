package food.booking.app.business.adapter.in.web.menu;

import food.booking.app.business.adapter.in.web.restaurant.RestaurantPreviewModel;
import food.booking.app.business.adapter.in.web.restaurant.RestaurantPreviewModelAssembler;
import food.booking.app.business.app.port.in.menu.CreateMenuCommand;
import food.booking.app.business.app.port.in.menu.CreateMenuUseCase;
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
 * Create menu controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
class CreateMenuController {

    private final MenuWebMapper menuWebMapper;

    private final CreateMenuUseCase createMenuUseCase;

    private final MenuModelAssembler menuModelAssembler;

    private final RestaurantPreviewModelAssembler restaurantPreviewModelAssembler;

    private final static String SELF = "self";

    /**
     * Create menu
     *
     * @param input create menu model
     * @return menu model
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaTypes.HAL_JSON_VALUE})
    ResponseEntity<RepresentationModel<?>> createMenu(@Valid @RequestBody Menu input) {
        log.debug("REST request to create menu: {}", input);
        MenuModel menu = executeCreateMenu(input);
        Link self = menu.getRequiredLink(SELF);
        menu.removeLinks();
        return ResponseEntity.created(self.toUri())
                .body(buildHalModel(menu, restaurantPreviewModelAssembler.toModel(input.restaurant()), self));
    }

    /**
     * Execute create menu
     *
     * @param input create menu model
     * @return menu model
     */
    private MenuModel executeCreateMenu(Menu input) {
        CreateMenuCommand command = menuWebMapper.mapToCommand(input);
        var menu = createMenuUseCase.create(command);
        return menuModelAssembler.toModel(menu);
    }

    /**
     * Build HAL model
     *
     * @param menu       menu model
     * @param restaurant restaurant preview model
     * @param self       self link
     * @return HAL representation model
     */
    private RepresentationModel<?> buildHalModel(MenuModel menu, RestaurantPreviewModel restaurant, Link self) {
        return HalModelBuilder.halModelOf(menu)
                .preview(restaurant)
                .forLink(restaurant.getRequiredLink(SELF).withRel("restaurantPreview"))
                .link(self)
                .build();
    }

}

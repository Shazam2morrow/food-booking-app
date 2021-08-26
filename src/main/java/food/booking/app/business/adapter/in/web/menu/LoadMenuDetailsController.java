package food.booking.app.business.adapter.in.web.menu;

import food.booking.app.business.app.port.in.menu.LoadMenuDetailsUseCase;
import food.booking.app.business.domain.Menu;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Load menu details controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
class LoadMenuDetailsController {

    private final MenuModelAssembler menuModelAssembler;

    private final LoadMenuDetailsUseCase loadMenuDetailsUseCase;

    /**
     * Load menu details
     *
     * @param menuSlug menu slug
     * @return menu model
     */
    @GetMapping(path = "/{menuSlug}", produces = {MediaTypes.HAL_JSON_VALUE})
    ResponseEntity<MenuModel> loadMenuDetails(@PathVariable String menuSlug) {
        log.debug("REST request to load menu details: {}", menuSlug);
        Menu menu = loadMenuDetailsUseCase.loadDetailsBySlug(menuSlug);
        return ResponseEntity.ok(menuModelAssembler.toModel(menu));
    }

}

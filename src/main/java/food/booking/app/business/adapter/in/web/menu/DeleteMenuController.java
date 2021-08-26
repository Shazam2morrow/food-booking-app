package food.booking.app.business.adapter.in.web.menu;

import food.booking.app.business.app.port.in.menu.DeleteMenuUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Delete menu controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
class DeleteMenuController {

    private final DeleteMenuUseCase deleteMenuUseCase;

    /**
     * Delete menu
     *
     * @param menuSlug menu slug
     * @return no content
     */
    @DeleteMapping(path = "/{menuSlug}")
    ResponseEntity<Void> deleteMenu(@PathVariable String menuSlug) {
        log.debug("Trying to delete menu: {}", menuSlug);
        deleteMenuUseCase.deleteBySlug(menuSlug);
        return ResponseEntity.noContent().build();
    }

}

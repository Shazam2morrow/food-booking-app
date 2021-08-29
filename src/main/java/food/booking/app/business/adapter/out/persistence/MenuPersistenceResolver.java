package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.domain.Menu;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;

import javax.persistence.EntityNotFoundException;

/**
 * Menu persistence resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class MenuPersistenceResolver {

    private final MenuRepository menuRepository;

    MenuJpaEntity resolve(Menu menu) {
        return resolve(requireValid(menu).getSlug());
    }

    MenuJpaEntity resolve(String menuSlug) {
        return menuRepository.findBySlug(requireValidSlug(menuSlug))
                .orElseThrow(() -> new EntityNotFoundException("Menu was not found " + menuSlug));
    }

    private Menu requireValid(Menu menu) {
        return Validate.notNull(menu, "menu can not be null");
    }

    private String requireValidSlug(String menuSlug) {
        return Validate.notBlank(menuSlug, "menuSlug can not be blank");
    }

}

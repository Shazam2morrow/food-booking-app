package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.in.menu.exception.MenuNotFoundException;
import food.booking.app.shared.HasSlug;
import lombok.RequiredArgsConstructor;

/**
 * Menu persistence resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class MenuPersistenceResolver {

    private final MenuRepository menuRepository;

    /**
     * Resolve menu jpa entity
     *
     * @param hasSlug slugable
     * @return resolved menu jpa entity
     * @throws MenuNotFoundException if menu was not found
     */
    MenuJpaEntity resolve(HasSlug<String> hasSlug) {
        return resolve(hasSlug.getSlug());
    }

    /**
     * Resolve menu entity
     *
     * @param menuSlug menu slug
     * @return menu entity
     * @throws MenuNotFoundException if menu was not found
     */
    MenuJpaEntity resolve(String menuSlug) {
        return menuRepository.findBySlug(menuSlug)
                .orElseThrow(() -> new MenuNotFoundException(menuSlug));
    }

}

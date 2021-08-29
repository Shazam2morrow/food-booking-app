package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.out.menu.*;
import food.booking.app.business.domain.Menu;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Menu persistence adapter
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class MenuSlugPersistenceAdapter implements CheckMenuSlugPort,
        CreateMenuPort,
        LoadRestaurantMenuListPort,
        UpdateMenuDetailsPort,
        LoadMenuDetailsPort {

    private final MenuRepository menuRepository;

    private final MenuPersistenceMapper menuPersistenceMapper;

    private final MenuPersistenceResolver menuPersistenceResolver;

    @Override
    public Menu create(CreateMenu createMenu) {
        MenuJpaEntity menu = menuPersistenceMapper.mapToJpaEntity(requireValid(createMenu));
        return menuPersistenceMapper.mapToDomainEntity(menuRepository.save(menu));
    }

    @Override
    public void update(UpdateMenuDetails details) {
        MenuJpaEntity menu = menuPersistenceResolver.resolve(requireValid(details).slug());
        menuPersistenceMapper.applyUpdatedDetails(menu, details);
    }

    @Override
    public boolean checkBySlug(String menuSlug) {
        return menuRepository.existsBySlug(requireValidSlug(menuSlug));
    }

    @Override
    public Menu loadDetailsBySlug(String menuSlug) {
        MenuJpaEntity menu = menuPersistenceResolver.resolve(requireValidSlug(menuSlug));
        return menuPersistenceMapper.mapToDomainEntity(menu);
    }

    @Override
    public List<Menu> loadRestaurantMenuList(String restaurantSlug) {
        return menuRepository.findAllByRestaurantSlugAndOrderBySortOrder(requireValidSlug(restaurantSlug))
                .stream()
                .map(menuPersistenceMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    /**
     * Validate object
     *
     * @param createMenu create menu
     * @return validated object
     */
    private CreateMenu requireValid(CreateMenu createMenu) {
        return Validate.notNull(createMenu, "createMenu can not be null");
    }

    /**
     * Validate object
     *
     * @param details update menu details
     * @return validated object
     */
    private UpdateMenuDetails requireValid(UpdateMenuDetails details) {
        return Validate.notNull(details, "updateMenuDetails can not be null");
    }

    /**
     * Require valid slug
     *
     * @param slug slug
     * @return validated slug
     */
    private String requireValidSlug(String slug) {
        return Validate.notBlank(slug, "slug can not be blank");
    }

}

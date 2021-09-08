package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.out.menu.*;
import food.booking.app.business.domain.Menu;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Menu persistence adapter
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class MenuPersistenceAdapter implements CheckMenuSlugPort,
        CreateMenuPort,
        LoadRestaurantMenuListPort,
        UpdateMenuDetailsPort,
        LoadMenuDetailsPort {

    private final MenuRepository menuRepository;

    private final MenuPersistenceMapper menuPersistenceMapper;

    private final MenuPersistenceResolver menuPersistenceResolver;

    @Override
    public Menu create(CreateMenu createMenu) {
        MenuJpaEntity menu = menuPersistenceMapper.mapToJpaEntity(createMenu);
        return menuPersistenceMapper.mapToDomainEntity(menuRepository.save(menu));
    }

    @Override
    public void update(UpdateMenuDetails details) {
        menuPersistenceMapper.applyUpdatedDetails(details);
    }

    @Override
    public boolean checkBySlug(String menuSlug) {
        return menuRepository.existsBySlug(menuSlug);
    }

    @Override
    public Menu loadDetailsBySlug(String menuSlug) {
        MenuJpaEntity menu = menuPersistenceResolver.resolve(menuSlug);
        return menuPersistenceMapper.mapToDomainEntity(menu);
    }

    @Override
    public List<Menu> loadRestaurantMenuList(String restaurantSlug) {
        return menuRepository.findAllByRestaurantSlugAndOrderBySortOrder(restaurantSlug)
                .stream()
                .map(menuPersistenceMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

}

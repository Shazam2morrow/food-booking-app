package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.in.restaurant.exception.RestaurantNotFoundException;
import food.booking.app.business.app.port.out.menu.CreateMenu;
import food.booking.app.business.app.port.out.menu.UpdateMenuDetails;
import food.booking.app.business.domain.Menu;
import lombok.RequiredArgsConstructor;

/**
 * Menu persistence mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class MenuPersistenceMapper {

    private final MenuPersistenceResolver menuPersistenceResolver;

    private final RestaurantPersistenceResolver restaurantPersistenceResolver;

    /**
     * Map to menu jpa entity
     *
     * @param createMenu create menu
     * @return menu jpa entity
     * @throws RestaurantNotFoundException if restaurant was not found
     */
    MenuJpaEntity mapToJpaEntity(CreateMenu createMenu) {
        return new MenuJpaEntity(
                restaurantPersistenceResolver.resolve(createMenu.restaurant()),
                createMenu.slug(),
                createMenu.title(),
                createMenu.sortOrder()
        );
    }

    /**
     * Map to menu domain entity
     *
     * @param entity entity
     * @return menu domain entity
     */
    Menu mapToDomainEntity(MenuJpaEntity entity) {
        return new Menu(
                entity.getSlug(),
                entity.getTitle(),
                entity.isActive(),
                entity.getSortOrder(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    /**
     * Apply updated details
     *
     * @param details update menu details
     * @return update menu details
     */
    Menu applyUpdatedDetails(UpdateMenuDetails details) {
        MenuJpaEntity entity = menuPersistenceResolver.resolve(details);
        entity.setTitle(details.title());
        entity.setActive(details.active());
        entity.setSortOrder(details.sortOrder());
        return mapToDomainEntity(entity);
    }

}

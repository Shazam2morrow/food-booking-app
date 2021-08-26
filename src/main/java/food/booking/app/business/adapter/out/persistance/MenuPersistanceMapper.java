package food.booking.app.business.adapter.out.persistance;

import food.booking.app.business.app.port.out.menu.CreateMenu;
import food.booking.app.business.app.port.out.menu.UpdateMenuDetails;
import food.booking.app.business.domain.Menu;
import lombok.RequiredArgsConstructor;

/**
 * Menu persistance mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class MenuPersistanceMapper {

    private final RestaurantPersistanceResolver restaurantPersistanceResolver;

    MenuJpaEntity mapToJpaEntity(CreateMenu createMenu) {
        return new MenuJpaEntity(
                restaurantPersistanceResolver.resolve(createMenu.restaurant()),
                createMenu.slug(),
                createMenu.title(),
                createMenu.sortOrder()
        );
    }

    Menu mapToDomainEntity(MenuJpaEntity entity) {
        return new Menu(
                entity.getSlug(),
                entity.getTitle(),
                entity.isActive(),
                entity.getSortOrder(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    void applyUpdatedDetails(MenuJpaEntity entity, UpdateMenuDetails details) {
        entity.setTitle(details.title());
        entity.setActive(details.active());
        entity.setSortOrder(details.sortOrder());
    }

}

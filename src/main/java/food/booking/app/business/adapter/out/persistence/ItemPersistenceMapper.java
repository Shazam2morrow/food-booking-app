package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.out.item.CreateItem;
import food.booking.app.business.app.port.out.item.UpdateItemDetails;
import food.booking.app.business.domain.Item;
import lombok.RequiredArgsConstructor;

/**
 * Item persistence mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class ItemPersistenceMapper {

    private final ItemPersistenceResolver itemPersistenceResolver;

    private final GroupPersistenceResolver groupPersistenceResolver;

    ItemJpaEntity mapToJpaEntity(CreateItem createItem) {
        return new ItemJpaEntity(
                createItem.slug(),
                createItem.title(),
                groupPersistenceResolver.resolve(createItem.group()),
                createItem.bannerUrl(),
                createItem.price(),
                createItem.calories(),
                createItem.sortOrder(),
                createItem.cookingTime(),
                createItem.description());
    }

    Item mapToDomainEntity(ItemJpaEntity entity) {
        return new Item(
                entity.getSlug(),
                entity.getTitle(),
                entity.getBannerUrl(),
                entity.isActive(),
                entity.getCalories(),
                entity.getPrice(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getSortOrder(),
                entity.getCookingTime(),
                entity.getDescription());
    }

    Item applyUpdatedDetails(UpdateItemDetails details) {
        ItemJpaEntity entity = itemPersistenceResolver.resolve(details);
        entity.setPrice(details.price());
        entity.setTitle(details.title());
        entity.setActive(details.active());
        entity.setCalories(details.calories());
        entity.setBannerUrl(details.bannerUrl());
        entity.setSortOrder(details.sortOrder());
        entity.setDescription(details.description());
        entity.setCookingTime(details.cookingTime());
        return mapToDomainEntity(entity);
    }

}

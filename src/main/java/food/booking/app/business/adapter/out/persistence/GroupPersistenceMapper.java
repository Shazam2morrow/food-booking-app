package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.in.group.exception.GroupNotFoundException;
import food.booking.app.business.app.port.in.menu.exception.MenuNotFoundException;
import food.booking.app.business.app.port.out.group.CreateGroup;
import food.booking.app.business.app.port.out.group.UpdateGroupDetails;
import food.booking.app.business.domain.Group;
import lombok.RequiredArgsConstructor;

/**
 * Group persistence mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class GroupPersistenceMapper {

    private final MenuPersistenceResolver menuPersistenceResolver;

    private final GroupPersistenceResolver groupPersistenceResolver;

    /**
     * Map to entity
     *
     * @param createGroup create group
     * @return group entity
     * @throws MenuNotFoundException if menu was not found
     */
    GroupJpaEntity mapToJpaEntity(CreateGroup createGroup) {
        return new GroupJpaEntity(
                menuPersistenceResolver.resolve(createGroup.menu()),
                createGroup.slug(),
                createGroup.title(),
                createGroup.sortOrder(),
                createGroup.iconURl());
    }

    /**
     * Map to domain entity
     *
     * @param entity group entity
     * @return group
     */
    Group mapToDomainEntity(GroupJpaEntity entity) {
        var group = new Group();
        group.setSlug(entity.getSlug());
        group.setTitle(entity.getTitle());
        group.setActive(entity.isActive());
        group.setIconUrl(entity.getIconUrl());
        group.setSortOrder(entity.getSortOrder());
        group.setCreatedAt(entity.getCreatedAt());
        group.setUpdatedAt(entity.getUpdatedAt());
        return group;
    }

    /**
     * Apply updated details
     *
     * @param details update group details
     * @throws GroupNotFoundException if group was not found
     */
    Group applyUpdatedDetails(UpdateGroupDetails details) {
        GroupJpaEntity entity = groupPersistenceResolver.resolve(details);
        entity.setTitle(details.title());
        entity.setActive(details.active());
        entity.setIconUrl(details.iconUrl());
        entity.setSortOrder(details.sortOrder());
        return mapToDomainEntity(entity);
    }

}

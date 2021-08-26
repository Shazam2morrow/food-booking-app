package food.booking.app.business.adapter.out.persistance;

import food.booking.app.business.app.port.out.group.CreateGroup;
import food.booking.app.business.app.port.out.group.UpdateGroupDetails;
import food.booking.app.business.domain.Group;
import lombok.RequiredArgsConstructor;

/**
 * Group persistance mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class GroupPersistanceMapper {

    private final MenuPersistanceResolver menuPersistanceResolver;

    GroupJpaEntity mapToJpaEntity(CreateGroup createGroup) {
        return new GroupJpaEntity(
                menuPersistanceResolver.resolve(createGroup.menu()),
                createGroup.slug(),
                createGroup.title(),
                createGroup.sortOrder(),
                createGroup.iconURl());
    }

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

    void applyUpdatedDetails(GroupJpaEntity entity, UpdateGroupDetails details) {
        entity.setTitle(details.title());
        entity.setActive(details.active());
        entity.setIconUrl(details.iconUrl());
        entity.setSortOrder(details.sortOrder());
    }

}

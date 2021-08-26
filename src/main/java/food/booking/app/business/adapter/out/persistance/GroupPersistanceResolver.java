package food.booking.app.business.adapter.out.persistance;

import food.booking.app.business.app.port.out.group.UpdateGroupDetails;
import food.booking.app.business.domain.Group;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;

import javax.persistence.EntityNotFoundException;

/**
 * Group persistance resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class GroupPersistanceResolver {

    private final GroupRepository groupRepository;

    GroupJpaEntity resolve(Group group) {
        Validate.notNull(group, "group can not be null");
        return resolve(group.getSlug());
    }

    GroupJpaEntity resolve(UpdateGroupDetails updateGroupDetails) {
        Validate.notNull(updateGroupDetails, "updateMenuGroupDetails can not be null");
        return resolve(updateGroupDetails.slug());
    }

    GroupJpaEntity resolve(String slug) {
        Validate.notBlank(slug, "slug can not be blank");
        return groupRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Menu group was not found " + slug));
    }

}

package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.in.group.exception.GroupNotFoundException;
import food.booking.app.shared.HasSlug;
import lombok.RequiredArgsConstructor;

/**
 * Group persistence resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class GroupPersistenceResolver {

    private final GroupRepository groupRepository;

    /**
     * Resolve group jpa entity
     *
     * @param hasSlug slugable
     * @return resolved group jpa entity
     * @throws GroupNotFoundException if group was not found
     */
    GroupJpaEntity resolve(HasSlug<String> hasSlug) {
        return resolve(hasSlug.getSlug());
    }

    /**
     * Resolve entity
     *
     * @param groupSlug group slug
     * @return group entity
     * @throws GroupNotFoundException if group was not found
     */
    GroupJpaEntity resolve(String groupSlug) {
        return groupRepository.findBySlug(groupSlug)
                .orElseThrow(() -> new GroupNotFoundException(groupSlug));
    }

}

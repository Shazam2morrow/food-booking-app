package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.out.group.*;
import food.booking.app.business.domain.Group;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Group persistence adapter
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class GroupPersistenceAdapter implements CreateGroupPort,
        CheckGroupSlugPort,
        LoadMenuGroupListPort,
        LoadGroupDetailsPort,
        UpdateGroupDetailsPort {

    private final GroupRepository groupRepository;

    private final GroupPersistenceMapper groupPersistenceMapper;

    private final GroupPersistenceResolver groupPersistenceResolver;

    @Override
    public Group create(CreateGroup createGroup) {
        GroupJpaEntity group = groupPersistenceMapper.mapToJpaEntity(requireValid(createGroup));
        return groupPersistenceMapper.mapToDomainEntity(groupRepository.save(group));
    }

    @Override
    public void update(UpdateGroupDetails details) {
        GroupJpaEntity group = groupPersistenceResolver.resolve(requireValid(details));
        groupPersistenceMapper.applyUpdatedDetails(group, details);
    }

    @Override
    public boolean checkBySlug(String groupSlug) {
        return groupRepository.existsBySlug(requireValidSlug(groupSlug));
    }

    @Override
    public Group loadDetailsBySlug(String groupSlug) {
        GroupJpaEntity group = groupPersistenceResolver.resolve(requireValidSlug(groupSlug));
        return groupPersistenceMapper.mapToDomainEntity(group);
    }

    @Override
    public List<Group> loadListByMenuSlug(String menuSlug) {
        return groupRepository.findAllByMenuSlugAndOrderBySortOrder(requireValidSlug(menuSlug))
                .stream()
                .map(groupPersistenceMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    /**
     * Validate object
     *
     * @param createGroup create group
     * @return validated object
     */
    private CreateGroup requireValid(CreateGroup createGroup) {
        return Validate.notNull(createGroup, "createGroup can not be null");
    }

    /**
     * Validate object
     *
     * @param details update group details
     * @return validated object
     */
    private UpdateGroupDetails requireValid(UpdateGroupDetails details) {
        return Validate.notNull(details, "updateGroupDetails can not be null");
    }

    /**
     * Validate slug
     *
     * @param slug slug
     * @return validated slug
     */
    private String requireValidSlug(String slug) {
        return Validate.notBlank(slug, "slug can not be blank");
    }

}

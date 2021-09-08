package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.out.group.*;
import food.booking.app.business.domain.Group;
import lombok.RequiredArgsConstructor;

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
        GroupJpaEntity group = groupPersistenceMapper.mapToJpaEntity(createGroup);
        return groupPersistenceMapper.mapToDomainEntity(groupRepository.save(group));
    }

    @Override
    public void update(UpdateGroupDetails details) {
        groupPersistenceMapper.applyUpdatedDetails(details);
    }

    @Override
    public boolean checkBySlug(String groupSlug) {
        return groupRepository.existsBySlug(groupSlug);
    }

    @Override
    public Group loadDetailsBySlug(String groupSlug) {
        GroupJpaEntity group = groupPersistenceResolver.resolve(groupSlug);
        return groupPersistenceMapper.mapToDomainEntity(group);
    }

    @Override
    public List<Group> loadListByMenuSlug(String menuSlug) {
        return groupRepository.findAllByMenuSlugAndOrderBySortOrder(menuSlug)
                .stream()
                .map(groupPersistenceMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

}

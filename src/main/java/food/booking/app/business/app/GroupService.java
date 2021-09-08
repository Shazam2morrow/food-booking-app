package food.booking.app.business.app;

import food.booking.app.business.app.port.in.group.*;
import food.booking.app.business.app.port.in.group.exception.GroupServiceException;
import food.booking.app.business.app.port.out.group.*;
import food.booking.app.business.domain.Group;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Group service
 *
 * @author shazam2morrow
 */
@Slf4j
@Validated
@RequiredArgsConstructor
class GroupService implements CreateGroupUseCase,
        LoadMenuGroupListUseCase,
        LoadGroupDetailsUseCase,
        UpdateGroupDetailsUseCase,
        DeleteGroupUseCase {

    private final CreateGroupPort createGroupPort;

    private final GroupServiceMapper groupServiceMapper;

    private final LoadGroupDetailsPort loadGroupDetailsPort;

    private final LoadMenuGroupListPort loadMenuGroupListPort;

    private final UpdateGroupDetailsPort updateGroupDetailsPort;

    @Override
    @Transactional
    public Group create(CreateGroupCommand command) {
        log.debug("Trying to create group: {}", command);
        try {
            CreateGroup group = groupServiceMapper.mapToCreateGroup(requireValid(command));
            return createGroupPort.create(group);
        } catch (Exception ex) {
            throw new GroupServiceException("Failed to create group!", ex);
        }
    }

    @Override
    @Transactional
    public void update(UpdateGroupDetailsCommand command) {
        log.debug("Trying to update group details: {}", command);
        try {
            UpdateGroupDetails details = groupServiceMapper.mapToUpdateGroupDetails(requireValid(command));
            updateGroupDetailsPort.update(details);
        } catch (Exception ex) {
            throw new GroupServiceException("Failed to update group details!", ex);
        }
    }

    @Override
    @Transactional
    public void deleteBySlug(String groupSlug) {
        log.debug("Trying to delete group: {}", groupSlug);
        Group group = loadDetailsBySlug(groupSlug);
        if (group.isActive()) {
            group.setActive(Boolean.FALSE);
            update(groupServiceMapper.mapToUpdateGroupDetailsCommand(group));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Group> loadListByMenuSlug(String menuSlug) {
        log.debug("Trying to load list of menu groups: {}", menuSlug);
        return loadMenuGroupListPort.loadListByMenuSlug(requireValidSlug(menuSlug));
    }

    @Override
    @Transactional(readOnly = true)
    public Group loadDetailsBySlug(String groupSlug) {
        log.debug("Trying to load group details: {}", groupSlug);
        return loadGroupDetailsPort.loadDetailsBySlug(requireValidSlug(groupSlug));
    }

    /**
     * Validate object
     *
     * @param command create group command
     * @return validated object
     */
    private CreateGroupCommand requireValid(CreateGroupCommand command) {
        return Validate.notNull(command, "createMenuCommand can not be null");
    }

    /**
     * Validate object
     *
     * @param command update group details command
     * @return validated object
     */
    private UpdateGroupDetailsCommand requireValid(UpdateGroupDetailsCommand command) {
        return Validate.notNull(command, "updateGroupDetailsCommand can not be null");
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

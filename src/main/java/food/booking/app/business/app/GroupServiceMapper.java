package food.booking.app.business.app;

import food.booking.app.business.app.port.in.group.CreateGroupCommand;
import food.booking.app.business.app.port.in.group.UpdateGroupDetailsCommand;
import food.booking.app.business.app.port.out.group.CheckGroupSlugPort;
import food.booking.app.business.app.port.out.group.CreateGroup;
import food.booking.app.business.app.port.out.group.UpdateGroupDetails;
import food.booking.app.business.domain.Group;
import food.booking.app.shared.size.SlugSize;
import food.booking.app.storage.app.FileUrlResolver;
import food.booking.app.storage.app.port.in.exception.FileUrlNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;

/**
 * Group service mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class GroupServiceMapper {

    private final FileUrlResolver fileUrlResolver;

    private final CheckGroupSlugPort checkGroupSlugPort;

    private final RandomStringGenerator randomStringGenerator;

    /**
     * Map to create group
     *
     * @param command create group command
     * @return create group
     * @throws FileUrlNotFoundException if icon was not found
     */
    CreateGroup mapToCreateGroup(CreateGroupCommand command) {
        return new CreateGroup(
                command.getMenu(),
                generateSlug(),
                command.getTitle(),
                command.getSortOrder(),
                fileUrlResolver.resolve(command.getIconUrl()));
    }

    /**
     * Map to update group details
     *
     * @param command update group details command
     * @return update group details
     */
    UpdateGroupDetails mapToUpdateGroupDetails(UpdateGroupDetailsCommand command) {
        return new UpdateGroupDetails(
                command.getSlug(),
                command.getTitle(),
                command.getSortOrder(),
                command.getIconUrl(),
                command.isActive());
    }

    /**
     * Map to update group details command
     *
     * @param group group
     * @return update group details command
     */
    UpdateGroupDetailsCommand mapToUpdateGroupDetailsCommand(Group group) {
        return new UpdateGroupDetailsCommand(
                group.getSlug(),
                group.getTitle(),
                group.getActive(),
                group.getSortOrder(),
                group.getIconUrl());
    }

    /**
     * Generate unique slug for a new group
     *
     * @return group slug
     */
    private String generateSlug() {
        String slug;
        do {
            slug = randomStringGenerator.generate(SlugSize.MAX);
        } while (checkGroupSlugPort.checkBySlug(slug));
        return slug;
    }

}

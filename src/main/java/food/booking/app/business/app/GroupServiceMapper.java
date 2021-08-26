package food.booking.app.business.app;

import food.booking.app.business.app.port.in.group.CreateGroupCommand;
import food.booking.app.business.app.port.in.group.UpdateGroupDetailsCommand;
import food.booking.app.business.app.port.out.group.CheckGroupSlugPort;
import food.booking.app.business.app.port.out.group.CreateGroup;
import food.booking.app.business.app.port.out.group.UpdateGroupDetails;
import food.booking.app.shared.size.SlugSize;
import food.booking.app.storage.app.FileUriResolver;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;

/**
 * Group service mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class GroupServiceMapper {

    private final FileUriResolver fileUriResolver;

    private final CheckGroupSlugPort checkGroupSlugPort;

    private final RandomStringGenerator randomStringGenerator;

    CreateGroup mapToCreateGroup(CreateGroupCommand command) {
        return new CreateGroup(
                command.getMenu(),
                generateSlug(),
                command.getTitle(),
                command.getSortOrder(),
                fileUriResolver.resolve(command.getIconUrl()));
    }

    UpdateGroupDetails mapToUpdateGroupDetails(UpdateGroupDetailsCommand command) {
        return new UpdateGroupDetails(
                command.getSlug(),
                command.getTitle(),
                command.getSortOrder(),
                fileUriResolver.resolve(command.getIconUrl()),
                command.isActive());
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

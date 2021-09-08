package food.booking.app.business.app;

import food.booking.app.business.app.port.in.item.CreateItemCommand;
import food.booking.app.business.app.port.in.item.UpdateItemDetailsCommand;
import food.booking.app.business.app.port.out.item.CheckItemSlugPort;
import food.booking.app.business.app.port.out.item.CreateItem;
import food.booking.app.business.app.port.out.item.UpdateItemDetails;
import food.booking.app.business.domain.Item;
import food.booking.app.shared.size.SlugSize;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;

/**
 * Item service mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class ItemServiceMapper {

    private final CheckItemSlugPort checkItemSlugPort;

    private final RandomStringGenerator randomStringGenerator;

    CreateItem mapToCreateMenuItem(CreateItemCommand command) {
        return new CreateItem(
                generateSlug(),
                command.getTitle(),
                command.getGroup(),
                command.getPrice(),
                command.getCalories(),
                command.getSortOrder(),
                command.getCookingTime(),
                command.getDescription(),
                command.getBannerUrl());
    }

    UpdateItemDetailsCommand mapToUpdateItemDetailsCommand(Item item) {
        return new UpdateItemDetailsCommand(
                item.getSlug(),
                item.getTitle(),
                item.getBannerUrl(),
                item.getDescription(),
                item.getActive(),
                item.getPrice(),
                item.getCalories(),
                item.getSortOrder(),
                item.getCookingTime());
    }

    UpdateItemDetails mapToUpdateItemDetails(UpdateItemDetailsCommand command) {
        return new UpdateItemDetails(
                command.getSlug(),
                command.getTitle(),
                command.getBannerUrl(),
                command.getActive(),
                command.getPrice(),
                command.getCalories(),
                command.getSortOrder(),
                command.getCookingTime(),
                command.getDescription()
        );
    }

    /**
     * Generate unique slug for a new item
     *
     * @return menu item slug
     */
    private String generateSlug() {
        String slug;
        do {
            slug = randomStringGenerator.generate(SlugSize.MAX);
        } while (checkItemSlugPort.checkBySlug(slug));
        return slug;
    }

}

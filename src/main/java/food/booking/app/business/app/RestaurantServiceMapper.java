package food.booking.app.business.app;

import food.booking.app.business.app.port.in.restaurant.CreateRestaurantCommand;
import food.booking.app.business.app.port.in.restaurant.UpdateRestaurantDetailsCommand;
import food.booking.app.business.app.port.out.restaurant.CheckRestaurantSlugPort;
import food.booking.app.business.app.port.out.restaurant.CreateRestaurant;
import food.booking.app.business.app.port.out.restaurant.UpdateRestaurantDetails;
import food.booking.app.shared.size.SlugSize;
import food.booking.app.storage.app.FileUriResolver;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;

/**
 * Restaurant app mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class RestaurantServiceMapper {

    private final FileUriResolver fileUriResolver;

    private final CheckRestaurantSlugPort checkRestaurantSlugPort;

    private final RandomStringGenerator randomStringGenerator;

    CreateRestaurant mapToCreateRestaurant(CreateRestaurantCommand command) {
        return new CreateRestaurant(
                generateSlug(),
                command.getTitle(),
                command.getAddress(),
                command.getType(),
                fileUriResolver.resolve(command.getBannerUrl()),
                command.getLocation(),
                fileUriResolver.resolve(command.getImages()),
                command.getShortTitle(),
                command.getDescription(),
                command.getAliases(),
                command.getAverageReceipt(),
                command.getCategories(),
                command.getSchedule());
    }

    UpdateRestaurantDetails mapToUpdateRestaurantDetails(UpdateRestaurantDetailsCommand command) {
        return new UpdateRestaurantDetails(
                command.getSlug(),
                command.getTitle(),
                fileUriResolver.resolve(command.getBannerUrl()),
                command.getAddress(),
                command.getActive(),
                fileUriResolver.resolve(command.getMedia()),
                command.getLocation(),
                command.getShortTitle(),
                command.getDescription(),
                command.getAliases(),
                command.getAverageReceipt(),
                command.getCategories(),
                command.getSchedule()
        );
    }

    /**
     * Generate unique slug for a new restaurant
     *
     * @return restaurant slug
     */
    private String generateSlug() {
        String slug;
        do {
            slug = randomStringGenerator.generate(SlugSize.MAX);
        } while (checkRestaurantSlugPort.checkBySlug(slug));
        return slug;
    }

}

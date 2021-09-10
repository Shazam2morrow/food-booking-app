package food.booking.app.business.app;

import food.booking.app.business.app.port.in.restaurant.CreateRestaurantCommand;
import food.booking.app.business.app.port.in.restaurant.UpdateRestaurantDetailsCommand;
import food.booking.app.business.app.port.out.restaurant.CreateRestaurant;
import food.booking.app.business.app.port.out.restaurant.UpdateRestaurantDetails;
import food.booking.app.business.domain.Restaurant;
import food.booking.app.shared.CanCheckSlug;
import food.booking.app.shared.SlugGenerator;
import food.booking.app.storage.app.FileUrlResolver;
import food.booking.app.storage.app.port.in.exception.FileNotFoundException;
import org.apache.commons.text.RandomStringGenerator;

/**
 * Restaurant app mapper
 *
 * @author shazam2morrow
 */
class RestaurantServiceMapper extends SlugGenerator {

    private final FileUrlResolver fileUrlResolver;

    public RestaurantServiceMapper(CanCheckSlug canCheckSlug,
                                   FileUrlResolver fileUrlResolver,
                                   RandomStringGenerator randomStringGenerator) {
        super(canCheckSlug, randomStringGenerator);
        this.fileUrlResolver = fileUrlResolver;
    }

    /**
     * Map to create restaurant
     *
     * @param command create restaurant command
     * @return create restaurant
     * @throws FileNotFoundException if file url was not found
     */
    CreateRestaurant mapToCreateRestaurant(CreateRestaurantCommand command) {
        return new CreateRestaurant(
                generateSlug(),
                command.getTitle(),
                command.getAddress(),
                command.getType(),
                fileUrlResolver.resolve(command.getBannerUrl()),
                command.getLocation(),
                fileUrlResolver.resolve(command.getMedia()),
                command.getShortTitle(),
                command.getDescription(),
                command.getAliases(),
                command.getAverageReceipt(),
                command.getCategories(),
                command.getSchedule());
    }

    /**
     * Map to update restaurant details command
     *
     * @param restaurant restaurant
     * @return update restaurant details command
     */
    UpdateRestaurantDetailsCommand mapToUpdateRestaurantDetailsCommand(Restaurant restaurant) {
        return new UpdateRestaurantDetailsCommand(
                restaurant.getSlug(),
                restaurant.getTitle(),
                restaurant.getAddress(),
                restaurant.isActive(),
                restaurant.getBannerUrl(),
                restaurant.getMedia(),
                restaurant.getShortTitle(),
                restaurant.getLocation(),
                restaurant.getDescription(),
                restaurant.getAliases(),
                restaurant.getAverageReceipt(),
                restaurant.getCategories(),
                restaurant.getSchedule());
    }

    /**
     * Map to update restaurant details
     *
     * @param command update restaurant details command
     * @return update restaurant details
     * @throws FileNotFoundException if file url was not found
     */
    UpdateRestaurantDetails mapToUpdateRestaurantDetails(UpdateRestaurantDetailsCommand command) {
        return new UpdateRestaurantDetails(
                command.getSlug(),
                command.getTitle(),
                fileUrlResolver.resolve(command.getBannerUrl()),
                command.getAddress(),
                command.getActive(),
                fileUrlResolver.resolve(command.getMedia()),
                command.getLocation(),
                command.getShortTitle(),
                command.getDescription(),
                command.getAliases(),
                command.getAverageReceipt(),
                command.getCategories(),
                command.getSchedule()
        );
    }

}

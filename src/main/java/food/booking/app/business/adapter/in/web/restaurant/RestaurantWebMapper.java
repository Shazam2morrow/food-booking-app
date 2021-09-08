package food.booking.app.business.adapter.in.web.restaurant;

import food.booking.app.business.app.port.in.restaurant.CreateRestaurantCommand;

/**
 * Restaurant web mapper
 *
 * @author shazam2morrow
 */
class RestaurantWebMapper {

    /**
     * Map to command
     *
     * @param restaurant restaurant
     * @return create restaurant command
     */
    CreateRestaurantCommand mapToCommand(Restaurant restaurant) {
        return new CreateRestaurantCommand(
                restaurant.title(),
                restaurant.address(),
                restaurant.type(),
                restaurant.schedule(),
                restaurant.bannerUrl(),
                restaurant.media(),
                restaurant.shortTitle(),
                restaurant.location(),
                restaurant.description(),
                restaurant.aliases(),
                restaurant.averageReceipt(),
                restaurant.categories());
    }

}

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
     * @param model create restaurant model
     * @return create restaurant command
     */
    CreateRestaurantCommand mapToCommand(CreateRestaurantModel model) {
        return new CreateRestaurantCommand(
                model.title(),
                model.address(),
                model.type(),
                model.schedule(),
                model.bannerUrl(),
                model.media(),
                model.shortTitle(),
                model.location(),
                model.description(),
                model.aliases(),
                model.averageReceipt(),
                model.categories());
    }

}

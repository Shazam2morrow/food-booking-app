package food.booking.app.business.adapter.in.web.item;

import food.booking.app.business.app.port.in.item.CreateItemCommand;

/**
 * Item web mapper
 *
 * @author shazam2morrow
 */
class ItemWebMapper {

    /**
     * Map to command
     *
     * @param model model
     * @return create item command
     */
    CreateItemCommand mapToCommand(CreateItemModel model) {
        return new CreateItemCommand(
                model.group(),
                model.title(),
                model.bannerUrl(),
                model.price(),
                model.calories(),
                model.sortOrder(),
                model.cookingTime(),
                model.description());
    }

}

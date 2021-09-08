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
    CreateItemCommand mapToCommand(Item model) {
        return new CreateItemCommand(
                model.title(),
                model.description(),
                model.group(),
                model.price(),
                model.calories(),
                model.sortOrder(),
                model.cookingTime(),
                model.bannerUrl());
    }

}

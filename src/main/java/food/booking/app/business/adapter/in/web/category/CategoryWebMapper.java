package food.booking.app.business.adapter.in.web.category;

import food.booking.app.business.app.port.in.category.CreateCategoryCommand;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Category web mapper
 *
 * @author shazam2morrow
 */
class CategoryWebMapper {

    @Nullable
    CreateCategoryCommand mapToCommand(CreateCategoryModel model) {
        if (Objects.isNull(model)) {
            return null;
        }
        return new CreateCategoryCommand(model.title(), model.iconUrl(), model.sortOrder());
    }

}

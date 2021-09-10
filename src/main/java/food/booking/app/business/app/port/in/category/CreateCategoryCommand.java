package food.booking.app.business.app.port.in.category;

import food.booking.app.shared.validation.FileUrl;
import food.booking.app.shared.validation.SortOrder;
import food.booking.app.shared.validation.Title;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.net.URI;

/**
 * Create category command
 *
 * @author shazam2morrow
 */
@Getter
@RequiredArgsConstructor
public class CreateCategoryCommand {

    @FileUrl
    private final URI iconUrl;

    @Title
    private final String title;

    @SortOrder
    private final Short sortOrder;

    @Override
    public String toString() {
        return "category";
    }

}

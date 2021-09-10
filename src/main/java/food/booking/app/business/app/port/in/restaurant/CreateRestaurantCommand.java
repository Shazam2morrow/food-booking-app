package food.booking.app.business.app.port.in.restaurant;

import food.booking.app.business.adapter.out.persistence.RestaurantType;
import food.booking.app.business.domain.Category;
import food.booking.app.shared.domain.DailySchedule;
import food.booking.app.shared.domain.Location;
import food.booking.app.shared.validation.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

/**
 * Create restaurant command
 *
 * @author shazam2morrow
 */
@Getter
@RequiredArgsConstructor
public class CreateRestaurantCommand {

    @Title
    private final String title;

    @Address
    private final String address;

    @Type
    private final RestaurantType type;

    @Schedule
    private final List<@Valid DailySchedule> schedule;

    @FileUrl
    private final URI bannerUrl;

    @FileUrlList
    private final List<URI> media;

    @ShortTitle
    private final String shortTitle;

    @Valid
    private final Location location;

    @Description
    private final String description;

    @Aliases
    private final List<String> aliases;

    @AverageReceipt
    private final BigDecimal averageReceipt;

    @Nullable
    private final List<Category> categories;

    @Override
    public String toString() {
        return "restaurant";
    }

}

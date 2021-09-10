package food.booking.app.business.app.port.in.restaurant;

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
 * Update restaurant details command
 *
 * @author shazam2morrow
 */
@Getter
@RequiredArgsConstructor
public class UpdateRestaurantDetailsCommand {

    @Slug
    private final String slug;

    @Title
    private final String title;

    @Address
    private final String address;

    @Active
    private final Boolean active;

    @FileUrl
    private final URI bannerUrl;

    @FileUrl
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

    @Schedule
    private final List<@Valid DailySchedule> schedule;

    @Override
    public String toString() {
        return "restaurant";
    }

}

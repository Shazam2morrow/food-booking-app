package food.booking.app.business.app.port.in.restaurant;

import food.booking.app.business.domain.Category;
import food.booking.app.shared.SelfValidate;
import food.booking.app.shared.domain.DailySchedule;
import food.booking.app.shared.domain.Location;
import food.booking.app.shared.validation.*;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Update restaurant details command
 *
 * @author shazam2morrow
 */
@Getter
@ToString
public class UpdateRestaurantDetailsCommand extends SelfValidate<UpdateRestaurantDetailsCommand> {

    @Slug
    private final String slug;

    @Title
    private final String title;

    @Address
    private final String address;

    @NotNull(message = "active can not be null")
    private final Boolean active;

    @Nullable
    private final URI bannerUrl;

    @Nullable
    private final List<URI> media;

    @ShortTitle
    private final String shortTitle;

    @Nullable
    private final Location location;

    @Description
    private final String description;

    @Nullable
    private final List<String> aliases;

    @Nullable
    private final BigDecimal averageReceipt;

    @Nullable
    private final List<Category> categories;

    @NotNull(message = "schedule can not be null")
    @Size(min = 7, max = 7, message = "schedule must contain exactly 7 entities")
    List<DailySchedule> schedule;

    public UpdateRestaurantDetailsCommand(String slug,
                                          String title,
                                          Boolean active,
                                          String address,
                                          List<DailySchedule> schedule,
                                          @Nullable URI bannerUrl,
                                          @Nullable List<URI> media,
                                          @Nullable Location location,
                                          @Nullable String shortTitle,
                                          @Nullable String description,
                                          @Nullable List<String> aliases,
                                          @Nullable BigDecimal averageReceipt,
                                          @Nullable List<Category> categories) {
        this.slug = slug;
        this.title = title;
        this.active = active;
        this.address = address;
        this.schedule = schedule;
        this.location = location;
        this.bannerUrl = bannerUrl;
        this.shortTitle = shortTitle;
        this.description = description;
        this.averageReceipt = averageReceipt;
        this.media = Objects.isNull(media) ? new ArrayList<>() : media;
        this.aliases = Objects.isNull(aliases) ? new ArrayList<>() : aliases;
        this.categories = Objects.isNull(categories) ? new ArrayList<>() : categories;
        selfValidate();
    }

}

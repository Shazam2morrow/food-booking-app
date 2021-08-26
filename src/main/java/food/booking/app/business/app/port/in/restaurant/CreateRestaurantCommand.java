package food.booking.app.business.app.port.in.restaurant;

import food.booking.app.business.adapter.out.persistance.RestaurantType;
import food.booking.app.business.domain.Category;
import food.booking.app.shared.SelfValidate;
import food.booking.app.shared.domain.DailySchedule;
import food.booking.app.shared.domain.Location;
import food.booking.app.shared.validation.Address;
import food.booking.app.shared.validation.Description;
import food.booking.app.shared.validation.ShortTitle;
import food.booking.app.shared.validation.Title;
import lombok.Getter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Create restaurant command
 *
 * @author shazam2morrow
 */
@Getter
public class CreateRestaurantCommand extends SelfValidate<CreateRestaurantCommand> {

    @Title
    private final String title;

    @Address
    private final String address;

    @NotNull(message = "type can not be null")
    private final RestaurantType type;

    @NotNull(message = "schedule can not be null")
    @Size(min = 7, max = 7, message = "schedule must consist of 7 days")
    private final List<DailySchedule> schedule;

    @ShortTitle
    private final String shortTitle;

    @Description
    private final String description;

    @Nullable
    private final URI bannerUrl;

    @Nullable
    private final Location location;

    @Nullable
    private final List<URI> images;

    @Nullable
    private final List<String> aliases;

    @Nullable
    private final BigDecimal averageReceipt;

    @Nullable
    private final List<Category> categories;

    public CreateRestaurantCommand(String title,
                                   String address,
                                   RestaurantType type,
                                   List<DailySchedule> schedule,
                                   @Nullable URI bannerUrl,
                                   @Nullable List<URI> images,
                                   @Nullable String shortTitle,
                                   @Nullable Location location,
                                   @Nullable String description,
                                   @Nullable List<String> aliases,
                                   @Nullable BigDecimal averageReceipt,
                                   @Nullable List<Category> categories) {
        this.type = type;
        this.title = title;
        this.address = address;
        this.location = location;
        this.schedule = schedule;
        this.bannerUrl = bannerUrl;
        this.shortTitle = shortTitle;
        this.description = description;
        this.averageReceipt = averageReceipt;
        this.images = Objects.isNull(images) ? new ArrayList<>() : images;
        this.aliases = Objects.isNull(aliases) ? new ArrayList<>() : aliases;
        this.categories = Objects.isNull(categories) ? new ArrayList<>() : categories;
        selfValidate();
    }

}

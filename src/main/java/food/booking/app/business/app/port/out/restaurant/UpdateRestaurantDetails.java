package food.booking.app.business.app.port.out.restaurant;

import food.booking.app.business.domain.Category;
import food.booking.app.shared.Slugable;
import food.booking.app.shared.domain.DailySchedule;
import food.booking.app.shared.domain.Location;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Objects;

/**
 * Update restaurant details
 *
 * @author shazam2morrow
 */
public record UpdateRestaurantDetails(String slug,
                                      String title,
                                      URI bannerUrl,
                                      String address,
                                      Boolean active,
                                      List<URI> media,
                                      Location location,
                                      String shortTitle,
                                      String description,
                                      List<String> aliases,
                                      BigDecimal averageReceipt,
                                      List<Category> categories,
                                      List<DailySchedule> schedule) implements Slugable<String> {

    public boolean hasCategories() {
        return Objects.nonNull(categories) && !categories.isEmpty();
    }

    public String getSlug() {
        return slug;
    }

}

package food.booking.app.business.app.port.out.restaurant;

import food.booking.app.business.adapter.out.persistence.RestaurantType;
import food.booking.app.business.domain.Category;
import food.booking.app.shared.domain.DailySchedule;
import food.booking.app.shared.domain.Location;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

/**
 * Create restaurant
 *
 * @author shazam2morrow
 */
public record CreateRestaurant(String slug,
                               String title,
                               String address,
                               RestaurantType type,
                               URI bannerUrl,
                               Location location,
                               List<URI> media,
                               String shortTitle,
                               String description,
                               List<String> aliases,
                               BigDecimal averageReceipt,
                               List<Category> categories,
                               List<DailySchedule> schedule) {
}

package food.booking.app.business.app.port.out.item;

import food.booking.app.business.domain.Group;

import java.math.BigDecimal;
import java.net.URI;

/**
 * Create menu item
 *
 * @author shazam2morrow
 */
public record CreateItem(String slug,
                         String title,
                         Group group,
                         BigDecimal price,
                         Integer calories,
                         Integer sortOrder,
                         Short cookingTime,
                         String description,
                         URI bannerUrl) {
}

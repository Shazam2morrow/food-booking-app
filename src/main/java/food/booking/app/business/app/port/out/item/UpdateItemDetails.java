package food.booking.app.business.app.port.out.item;

import java.math.BigDecimal;
import java.net.URI;

/**
 * Update item details
 *
 * @author shazam2morrow
 */
public record UpdateItemDetails(String slug,
                                String title,
                                URI bannerUrl,
                                Boolean active,
                                BigDecimal price,
                                Integer calories,
                                Integer sortOrder,
                                Short cookingTime,
                                String description) {
}

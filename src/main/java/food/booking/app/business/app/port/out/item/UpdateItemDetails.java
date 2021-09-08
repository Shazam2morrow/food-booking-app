package food.booking.app.business.app.port.out.item;

import food.booking.app.shared.Slugable;

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
                                String description) implements Slugable<String> {

    public String getSlug() {
        return slug;
    }

}

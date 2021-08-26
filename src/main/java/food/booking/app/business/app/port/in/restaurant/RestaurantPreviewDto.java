package food.booking.app.business.app.port.in.restaurant;

import com.fasterxml.jackson.annotation.JsonInclude;
import food.booking.app.business.adapter.out.persistance.RestaurantType;
import lombok.Data;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

/**
 * Restaurant preview DTO
 *
 * @author shazam2morrow
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantPreviewDto {

    private String slug;
    private String title;
    private Short rating;
    private URI bannerUrl;
    private Boolean active;
    private String shortTitle;
    private RestaurantType type;
    private List<String> aliases;
    private BigDecimal averageReceipt;

}

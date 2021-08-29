package food.booking.app.business.adapter.out.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import food.booking.app.shared.persistence.PersistentEnum;
import lombok.Getter;

/**
 * Restaurant type enumeration
 *
 * @author shazam2morrow
 */
public enum RestaurantType implements PersistentEnum {

    @JsonProperty("shop")
    SHOP(1),

    @JsonProperty("chain")
    CHAIN(2),

    @JsonProperty("restaurant")
    RESTAURANT(3);

    @Getter
    private final short id;

    RestaurantType(int id) {
        this.id = (short) id;
    }

}

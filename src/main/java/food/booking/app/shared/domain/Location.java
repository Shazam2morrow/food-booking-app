package food.booking.app.shared.domain;

import food.booking.app.shared.SelfValidate;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Location
 *
 * @author shazam2morrow
 */
@Getter
public class Location extends SelfValidate<Location> {

    @NotNull(message = "longitude can not be null")
    @PositiveOrZero(message = "longitude must be positive")
    private final Double longitude;

    @NotNull(message = "latitude can not be null")
    @PositiveOrZero(message = "latitude must be positive")
    private final Double latitude;

    public Location(Double longitude, Double latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        selfValidate();
    }

}

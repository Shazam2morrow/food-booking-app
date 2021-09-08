package food.booking.app.shared.domain;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Location
 *
 * @author shazam2morrow
 */
@Getter
public class Location {

    /**
     * Longitude
     */
    @NotNull(message = "longitude.notnull")
    @Min(value = -180, message = "longitude.invalid.min")
    @Max(value = 80, message = "longitude.invalid.max")
    private final Double longitude;

    /**
     * Latitude
     */
    @NotNull(message = "latitude.notnull")
    @Min(value = -90, message = "latitude.invalid.min")
    @Max(value = 90, message = "latitude.invalid.max")
    private final Double latitude;

    public Location(Double longitude, Double latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}

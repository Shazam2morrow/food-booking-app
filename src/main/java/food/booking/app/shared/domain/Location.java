package food.booking.app.shared.domain;

import food.booking.app.shared.validation.Latitude;
import food.booking.app.shared.validation.Longitude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Location
 *
 * @author shazam2morrow
 */
@Getter
@RequiredArgsConstructor
public class Location {

    /**
     * Longitude
     */
    @Longitude
    private final Double longitude;

    /**
     * Latitude
     */
    @Latitude
    private final Double latitude;

}

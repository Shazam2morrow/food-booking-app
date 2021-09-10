package food.booking.app.shared.domain;

import food.booking.app.shared.validation.Hour;
import food.booking.app.shared.validation.Minute;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Time
 *
 * @author shazam2morrow
 */
@Getter
@RequiredArgsConstructor
public class Time {

    /**
     * Hour
     */
    @Hour
    private final Short hour;

    /**
     * Minute
     */
    @Minute
    private final Short minute;

}

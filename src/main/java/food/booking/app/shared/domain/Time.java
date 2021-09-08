package food.booking.app.shared.domain;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Time
 *
 * @author shazam2morrow
 */
@Getter
public class Time {

    /**
     * Hour
     */
    @NotNull(message = "hour.notnull")
    @Min(value = 0, message = "hour.invalid.min")
    @Max(value = 23, message = "hour.invalid.max")
    private final Short hour;

    /**
     * Minute
     */
    @NotNull(message = "minute.notnull")
    @Min(value = 0, message = "minute.invalid.min")
    @Max(value = 59, message = "minute.invalid.max")
    private final Short minute;

    public Time(Short hour, Short minute) {
        this.hour = hour;
        this.minute = minute;
    }

}

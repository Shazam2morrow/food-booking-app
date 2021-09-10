package food.booking.app.shared.domain;

import food.booking.app.business.adapter.out.persistence.WeekDay;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Daily schedule
 *
 * @author shazam2morrow
 */
@Getter
@RequiredArgsConstructor
public class DailySchedule {

    /**
     * Start week day
     */
    @NotNull(message = "startweekday.notnull")
    private final WeekDay startWeekDay;

    /**
     * Start at
     */
    @Valid
    @NotNull(message = "startat.notnull")
    private final Time startAt;

    /**
     * End week day
     */
    @NotNull(message = "endweekday.notnull")
    private final WeekDay endWeekDay;

    /**
     * End at
     */
    @Valid
    @NotNull(message = "endat.notnull")
    private final Time endAt;

}

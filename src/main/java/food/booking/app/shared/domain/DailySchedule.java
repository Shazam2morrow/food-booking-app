package food.booking.app.shared.domain;

import food.booking.app.business.adapter.out.persistance.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Daily schedule
 *
 * @author shazam2morrow
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailySchedule {

    private WeekDay startWeekDay;
    private Time startAt;
    private WeekDay endWeekDay;
    private Time endAt;

}

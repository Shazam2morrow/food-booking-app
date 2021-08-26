package food.booking.app.business.adapter.out.persistance;

import com.fasterxml.jackson.annotation.JsonProperty;
import food.booking.app.shared.persistance.PersistentEnum;
import lombok.Getter;

/**
 * Week day
 *
 * @author shazam2morrow
 */
public enum WeekDay implements PersistentEnum {

    @JsonProperty("monday")
    MONDAY(1),

    @JsonProperty("tuesday")
    TUESDAY(2),

    @JsonProperty("wednesday")
    WEDNESDAY(3),

    @JsonProperty("thursday")
    THURSDAY(4),

    @JsonProperty("friday")
    FRIDAY(5),

    @JsonProperty("saturday")
    SATURDAY(6),

    @JsonProperty("sunday")
    SUNDAY(7);

    @Getter
    private final short id;

    WeekDay(int id) {
        this.id = (short) id;
    }

}

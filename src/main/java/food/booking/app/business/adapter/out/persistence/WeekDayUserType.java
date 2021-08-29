package food.booking.app.business.adapter.out.persistence;

import food.booking.app.shared.persistence.BasePersistentEnumUserType;

/**
 * Week day user type
 *
 * @author shazam2morrow
 */
public class WeekDayUserType extends BasePersistentEnumUserType<WeekDay> {

    @Override
    public Class<WeekDay> returnedClass() {
        return WeekDay.class;
    }

}

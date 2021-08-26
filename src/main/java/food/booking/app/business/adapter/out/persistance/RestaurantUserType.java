package food.booking.app.business.adapter.out.persistance;

import food.booking.app.shared.persistance.BasePersistentEnumUserType;

/**
 * Restaurant user type
 *
 * @author shazam2morrow
 */
public class RestaurantUserType extends BasePersistentEnumUserType<RestaurantType> {

    @Override
    public Class<RestaurantType> returnedClass() {
        return RestaurantType.class;
    }

}

package food.booking.app.business.adapter.out.persistence;

import food.booking.app.shared.persistence.BasePersistentEnumUserType;

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

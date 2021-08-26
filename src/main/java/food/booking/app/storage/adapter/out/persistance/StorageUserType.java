package food.booking.app.storage.adapter.out.persistance;

import food.booking.app.shared.persistance.BasePersistentEnumUserType;

/**
 * Storage user type
 *
 * @author shazam2morrow
 */
public class StorageUserType extends BasePersistentEnumUserType<StorageType> {

    @Override
    public Class<StorageType> returnedClass() {
        return StorageType.class;
    }

}

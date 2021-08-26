package food.booking.app.storage.adapter.out.persistance;

import com.fasterxml.jackson.annotation.JsonProperty;
import food.booking.app.shared.persistance.PersistentEnum;
import lombok.Getter;

/**
 * Storage type enumeration
 *
 * @author shazam2morrow
 */
public enum StorageType implements PersistentEnum {

    @JsonProperty("local")
    LOCAL(1);

    @Getter
    private final short id;

    StorageType(int id) {
        this.id = (short) id;
    }

}

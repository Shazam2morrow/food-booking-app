package food.booking.app.business.app.port.out.restaurant;

import food.booking.app.business.domain.Restaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

/**
 * Load restaurant slice port
 *
 * @author shazam2morrow
 */
public interface LoadRestaurantSlicePort {

    /**
     * Load slice of restaurants
     *
     * @param page page
     * @return slice of restaurants
     */
    Slice<Restaurant> loadSlice(Pageable page);

}

package food.booking.app.business.app.port.in.restaurant;

import food.booking.app.business.domain.Restaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

/**
 * Load restaurant preview use case
 *
 * @author shazam2morrow
 */
public interface LoadRestaurantSliceUseCase {

    /**
     * Load restaurant preview
     *
     * @param page page
     * @return slice of restaurants
     */
    Slice<Restaurant> loadSlice(Pageable page);

}

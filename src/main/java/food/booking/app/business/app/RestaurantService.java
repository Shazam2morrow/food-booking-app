package food.booking.app.business.app;

import food.booking.app.business.app.port.in.restaurant.*;
import food.booking.app.business.app.port.out.restaurant.*;
import food.booking.app.business.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

/**
 * Restaurant service
 *
 * @author shazam2morrow
 */
@Slf4j
@RequiredArgsConstructor
class RestaurantService implements
        CreateRestaurantUseCase,
        UpdateRestaurantDetailsUseCase,
        LoadRestaurantDetailsUseCase,
        LoadRestaurantSliceUseCase, DeleteRestaurantUseCase {

    private final CreateRestaurantPort createRestaurantPort;

    private final LoadRestaurantSlicePort loadRestaurantSlicePort;

    private final RestaurantServiceMapper restaurantServiceMapper;

    private final LoadRestaurantDetailsPort loadRestaurantDetailsPort;

    private final UpdateRestaurantDetailsPort updateRestaurantDetailsPort;

    @Override
    @Transactional
    public Restaurant create(CreateRestaurantCommand command) {
        log.debug("Trying to create a new restaurant: {}", command);
        CreateRestaurant createRestaurant = restaurantServiceMapper.mapToCreateRestaurant(requireValid(command));
        return createRestaurantPort.create(createRestaurant);
    }

    @Override
    @Transactional
    public void update(UpdateRestaurantDetailsCommand command) {
        log.debug("Trying to update restaurant details: {}", command);
        UpdateRestaurantDetails details = restaurantServiceMapper.mapToUpdateRestaurantDetails(requireValid(command));
        updateRestaurantDetailsPort.update(details);
    }

    @Override
    @Transactional
    public void deleteBySlug(String restaurantSlug) {
        log.debug("Trying to delete restaurant: {}", restaurantSlug);
        Restaurant restaurant = loadDetailsBySlug(restaurantSlug);
        restaurant.setActive(Boolean.FALSE);
        var command = new UpdateRestaurantDetailsCommand(
                restaurant.getSlug(),
                restaurant.getTitle(),
                restaurant.isActive(),
                restaurant.getAddress(),
                restaurant.getSchedule(),
                restaurant.getBannerUrl(),
                restaurant.getMedia(),
                restaurant.getLocation(),
                restaurant.getShortTitle(),
                restaurant.getDescription(),
                restaurant.getAliases(),
                restaurant.getAverageReceipt(),
                restaurant.getCategories());
        update(command);
    }

    @Override
    @Transactional(readOnly = true)
    public Restaurant loadDetailsBySlug(String restaurantSlug) {
        log.debug("Trying to load restaurant details: {}", restaurantSlug);
        return loadRestaurantDetailsPort.loadDetailsBySlug(requireValidSlug(restaurantSlug));
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<Restaurant> loadSlice(Pageable page) {
        log.debug("Trying to load slice of restaurants: {}", page);
        return loadRestaurantSlicePort.loadSlice(requireValid(page));
    }

    /**
     * Validate object
     *
     * @param createRestaurantCommand object
     * @return validate object
     */
    private CreateRestaurantCommand requireValid(CreateRestaurantCommand createRestaurantCommand) {
        return Validate.notNull(createRestaurantCommand, "createRestaurantCommand can not be null");
    }

    /**
     * Validate object
     *
     * @param updateRestaurantDetailsCommand object
     * @return validate object
     */
    private UpdateRestaurantDetailsCommand requireValid(UpdateRestaurantDetailsCommand updateRestaurantDetailsCommand) {
        return Validate.notNull(updateRestaurantDetailsCommand, "updateRestaurantDetailsCommand can not be null");
    }

    /**
     * Validate object
     *
     * @param page page
     * @return validated object
     */
    private Pageable requireValid(Pageable page) {
        return Validate.notNull(page, "page can not be null");
    }

    /**
     * Validate restaurant slug
     *
     * @param restaurantSlug restaurant slug
     * @return validated restaurant slug
     */
    private String requireValidSlug(String restaurantSlug) {
        return Validate.notBlank(restaurantSlug, "restaurantSlug can not be blank");
    }

}

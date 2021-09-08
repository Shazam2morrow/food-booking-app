package food.booking.app.business.app;

import food.booking.app.business.app.port.in.restaurant.*;
import food.booking.app.business.app.port.in.restaurant.exception.RestaurantServiceException;
import food.booking.app.business.app.port.out.restaurant.*;
import food.booking.app.business.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Restaurant service
 *
 * @author shazam2morrow
 */
@Slf4j
@Validated
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
        log.debug("Trying to create restaurant: {}", command);
        try {
            CreateRestaurant restaurant = restaurantServiceMapper.mapToCreateRestaurant(requireValid(command));
            return createRestaurantPort.create(restaurant);
        } catch (Exception ex) {
            throw new RestaurantServiceException("Failed to create restaurant!", ex);
        }
    }

    @Override
    @Transactional
    public void update(UpdateRestaurantDetailsCommand command) {
        log.debug("Trying to update restaurant details: {}", command);
        try {
            UpdateRestaurantDetails details = restaurantServiceMapper.mapToUpdateRestaurantDetails(requireValid(command));
            updateRestaurantDetailsPort.update(details);
        } catch (Exception ex) {
            throw new RestaurantServiceException("Failed to update restaurant details!", ex);
        }
    }

    @Override
    @Transactional
    public void deleteBySlug(String restaurantSlug) {
        log.debug("Trying to delete restaurant: {}", restaurantSlug);
        Restaurant restaurant = loadDetailsBySlug(restaurantSlug);
        if (restaurant.isActive()) {
            restaurant.setActive(Boolean.FALSE);
            update(restaurantServiceMapper.mapToUpdateRestaurantDetailsCommand(restaurant));
        }
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
     * @param command create restaurant command
     * @return validated object
     */
    private CreateRestaurantCommand requireValid(CreateRestaurantCommand command) {
        return Validate.notNull(command, "createRestaurantCommand can not be null");
    }

    /**
     * Validate object
     *
     * @param command update restaurant details command
     * @return validated object
     */
    private UpdateRestaurantDetailsCommand requireValid(UpdateRestaurantDetailsCommand command) {
        return Validate.notNull(command, "updateRestaurantDetailsCommand can not be null");
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
     * Validate slug
     *
     * @param slug slug
     * @return validated slug
     */
    private String requireValidSlug(String slug) {
        return Validate.notBlank(slug, "slug can not be blank");
    }

}

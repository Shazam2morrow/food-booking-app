package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.out.restaurant.*;
import food.booking.app.business.domain.Category;
import food.booking.app.business.domain.Restaurant;
import food.booking.app.shared.domain.DailySchedule;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Restaurant persistence adapter
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class RestaurantSlugpersistenceAdapter implements
        CreateRestaurantPort,
        CheckRestaurantSlugPort,
        UpdateRestaurantDetailsPort,
        LoadRestaurantDetailsPort,
        LoadRestaurantSlicePort {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantPersistenceMapper restaurantPersistenceMapper;

    private final RestaurantScheduleRepository restaurantScheduleRepository;

    private final RestaurantCategoryRepository restaurantCategoryRepository;

    private final RestaurantPersistenceResolver restaurantPersistenceResolver;

    private final RestaurantSchedulePersistenceMapper restaurantSchedulePersistenceMapper;

    private final RestaurantCategoryPersistenceMapper restaurantCategoryPersistenceMapper;

    @Override
    public Restaurant create(CreateRestaurant createRestaurant) {
        RestaurantJpaEntity entity = saveRestaurant(requireValid(createRestaurant));
        Restaurant restaurant = restaurantPersistenceMapper.mapToDomainEntity(entity);
        restaurant.setCategories(saveCategories(createRestaurant.categories(), entity));
        restaurant.setSchedule(saveSchedule(createRestaurant.schedule(), entity));
        return restaurant;
    }

    @Override
    public void update(UpdateRestaurantDetails details) {
        RestaurantJpaEntity restaurant = restaurantPersistenceResolver.resolve(requireValid(details));
        restaurantPersistenceMapper.applyUpdatedDetails(restaurant, details);
        mergeSchedule(restaurant, details.schedule());
        mergeCategories(restaurant, details.categories());
    }

    @Override
    public boolean checkBySlug(String restaurantSlug) {
        return restaurantRepository.existsBySlug(requireValidSlug(restaurantSlug));
    }

    @Override
    public Slice<Restaurant> loadSlice(Pageable page) {
        return restaurantRepository.findAll(getPageOrDefault(page))
                .map(restaurantPersistenceMapper::mapToDomainEntity);
    }

    @Override
    public Restaurant loadDetailsBySlug(String restaurantSlug) {
        RestaurantJpaEntity entity = restaurantPersistenceResolver.resolve(requireValidSlug(restaurantSlug));
        Restaurant restaurant = restaurantPersistenceMapper.mapToDomainEntity(entity);
        restaurant.setSchedule(loadSchedule(entity));
        restaurant.setCategories(loadCategories(entity));
        return restaurant;
    }

    /**
     * Load schedule
     *
     * @param restaurant restaurant
     * @return list of daily schedules
     */
    private List<DailySchedule> loadSchedule(RestaurantJpaEntity restaurant) {
        return restaurantScheduleRepository.findAllByRestaurantOrderByStartWeekDay(restaurant)
                .stream()
                .map(restaurantSchedulePersistenceMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    /**
     * Load categories
     *
     * @param restaurant restaurant
     * @return list of restaurant categories
     */
    private List<Category> loadCategories(RestaurantJpaEntity restaurant) {
        return restaurantCategoryRepository.findAllByRestaurantOrderBySortOrder(restaurant)
                .stream()
                .map(restaurantCategoryPersistenceMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    /**
     * Merge schedule
     *
     * @param restaurant     restaurant
     * @param dailySchedules daily schedules
     */
    private void mergeSchedule(RestaurantJpaEntity restaurant, List<DailySchedule> dailySchedules) {
        restaurantScheduleRepository.deleteByRestaurant(restaurant);
        List<RestaurantScheduleJpaEntity> schedule = restaurantSchedulePersistenceMapper.mapToJpaEntities(
                dailySchedules, restaurant);
        restaurantScheduleRepository.saveAll(schedule);
    }

    /**
     * Merge categories
     *
     * @param restaurant   restaurant
     * @param categoryList category list
     */
    private void mergeCategories(RestaurantJpaEntity restaurant, List<Category> categoryList) {
        restaurantCategoryRepository.deleteByRestaurant(restaurant);
        List<RestaurantCategoryJpaEntity> categories = restaurantCategoryPersistenceMapper.mapToJpaEntities(
                categoryList, restaurant);
        restaurantCategoryRepository.saveAll(categories);
    }

    /**
     * Save restaurant
     *
     * @param createRestaurant create restaurant
     * @return saved restaurant
     */
    private RestaurantJpaEntity saveRestaurant(CreateRestaurant createRestaurant) {
        RestaurantJpaEntity restaurant = restaurantPersistenceMapper.mapToJpaEntity(createRestaurant);
        return restaurantRepository.save(restaurant);
    }

    /**
     * Save schedule
     *
     * @param dailySchedules daily schedules
     * @param restaurant     restaurant
     * @return saved schedule
     */
    private List<DailySchedule> saveSchedule(List<DailySchedule> dailySchedules, RestaurantJpaEntity restaurant) {
        return restaurantScheduleRepository.saveAll(restaurantSchedulePersistenceMapper.mapToJpaEntities(dailySchedules, restaurant))
                .stream()
                .map(restaurantSchedulePersistenceMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    /**
     * Save categories
     *
     * @param categories categories
     * @param restaurant restaurant
     * @return saved categories
     */
    private List<Category> saveCategories(List<Category> categories, RestaurantJpaEntity restaurant) {
        return restaurantCategoryRepository.saveAll(restaurantCategoryPersistenceMapper.mapToJpaEntities(categories, restaurant))
                .stream()
                .map(restaurantCategoryPersistenceMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    /**
     * Validate object
     *
     * @param createRestaurant create restaurant
     * @return validated object
     */
    private CreateRestaurant requireValid(CreateRestaurant createRestaurant) {
        return Validate.notNull(createRestaurant, "createRestaurant can not be null");
    }

    /**
     * Validate object
     *
     * @param details update restaurant details
     * @return validate object
     */
    private UpdateRestaurantDetails requireValid(UpdateRestaurantDetails details) {
        return Validate.notNull(details, "updateRestaurantDetails can not be null");
    }

    /**
     * Validate restaurant slug
     *
     * @param restaurantSlug restaurant slug
     * @return validate restaurant slug
     */
    private String requireValidSlug(String restaurantSlug) {
        return Validate.notBlank(restaurantSlug, "restaurantSlug can not be blank");
    }

    /**
     * Require valid page
     *
     * @param page page
     * @return validate page
     */
    private Pageable requireValidPage(Pageable page) {
        return Validate.notNull(page, "page can not be null");
    }

    /**
     * Get page or default one
     * <p>
     * Default page sorts by 'rating' property!
     *
     * @param page page
     * @return page containing sorting or page with default one
     */
    private Pageable getPageOrDefault(Pageable page) {
        Sort sort = requireValidPage(page).getSortOr(Sort.by(Sort.Direction.DESC, "rating"));
        return PageRequest.of(page.getPageNumber(), page.getPageSize(), sort);
    }

}

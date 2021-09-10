package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.out.restaurant.*;
import food.booking.app.business.domain.Category;
import food.booking.app.business.domain.Restaurant;
import food.booking.app.shared.domain.DailySchedule;
import lombok.RequiredArgsConstructor;
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
class RestaurantPersistenceAdapter implements
        CreateRestaurantPort,
        CheckRestaurantCanCheckSlugPort,
        UpdateRestaurantDetailsPort,
        LoadRestaurantDetailsPort,
        LoadRestaurantSlicePort {

    private final RestaurantRepository restaurantRepository;

    private final CategoryPersistenceMapper categoryPersistenceMapper;

    private final RestaurantPersistenceMapper restaurantPersistenceMapper;

    private final RestaurantScheduleRepository restaurantScheduleRepository;

    private final RestaurantCategoryRepository restaurantCategoryRepository;

    private final RestaurantPersistenceResolver restaurantPersistenceResolver;

    private final RestaurantSchedulePersistenceMapper restaurantSchedulePersistenceMapper;

    private final RestaurantCategoryPersistenceMapper restaurantCategoryPersistenceMapper;

    @Override
    public Restaurant create(CreateRestaurant createRestaurant) {
        Restaurant restaurant = saveRestaurant(createRestaurant);
        restaurant.setSchedule(saveSchedule(createRestaurant.schedule(), restaurant));
        if (createRestaurant.hasCategories()) {
            restaurant.setCategories(saveCategories(createRestaurant.categories(), restaurant));
        }
        return restaurant;
    }

    @Override
    public void update(UpdateRestaurantDetails details) {
        Restaurant restaurant = restaurantPersistenceMapper.applyUpdatedDetails(details);
        mergeSchedule(restaurant, details.schedule());
        if (details.hasCategories()) {
            mergeCategories(restaurant, details.categories());
        }
    }

    @Override
    public boolean check(String restaurantSlug) {
        return restaurantRepository.existsBySlug(restaurantSlug);
    }

    @Override
    public Slice<Restaurant> loadSlice(Pageable page) {
        return restaurantRepository.findAll(getPageOrDefault(page))
                .map(restaurantPersistenceMapper::mapToDomainEntity);
    }

    @Override
    public Restaurant loadDetailsBySlug(String restaurantSlug) {
        RestaurantJpaEntity entity = restaurantPersistenceResolver.resolve(restaurantSlug);
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
     * @return list of categories
     */
    private List<Category> loadCategories(RestaurantJpaEntity restaurant) {
        return restaurantCategoryRepository.findAllByRestaurantOrderBySortOrder(restaurant)
                .stream()
                .map(entity -> categoryPersistenceMapper.mapToDomainEntity(entity.getCategory()))
                .collect(Collectors.toList());
    }

    /**
     * Merge schedule
     *
     * @param restaurant     restaurant
     * @param dailySchedules daily schedules
     */
    private void mergeSchedule(Restaurant restaurant, List<DailySchedule> dailySchedules) {
        restaurantScheduleRepository.deleteByRestaurant(restaurantPersistenceResolver.resolve(restaurant));
        restaurantScheduleRepository.saveAll(restaurantSchedulePersistenceMapper.mapToJpaEntities(dailySchedules, restaurant));
    }

    /**
     * Merge categories
     *
     * @param restaurant   restaurant
     * @param categoryList category list
     */
    private void mergeCategories(Restaurant restaurant, List<Category> categoryList) {
        restaurantCategoryRepository.deleteByRestaurant(restaurantPersistenceResolver.resolve(restaurant));
        restaurantCategoryRepository.saveAll(restaurantCategoryPersistenceMapper.mapToJpaEntities(categoryList, restaurant));
    }

    /**
     * Save restaurant
     *
     * @param createRestaurant create restaurant
     * @return saved restaurant
     */
    private Restaurant saveRestaurant(CreateRestaurant createRestaurant) {
        RestaurantJpaEntity restaurant = restaurantPersistenceMapper.mapToJpaEntity(createRestaurant);
        return restaurantPersistenceMapper.mapToDomainEntity(restaurantRepository.save(restaurant));
    }

    /**
     * Save schedule
     *
     * @param dailySchedules daily schedules
     * @param restaurant     restaurant
     * @return saved schedule
     */
    private List<DailySchedule> saveSchedule(List<DailySchedule> dailySchedules, Restaurant restaurant) {
        return restaurantSchedulePersistenceMapper.mapToJpaEntities(dailySchedules, restaurant)
                .stream()
                .map(restaurantScheduleRepository::save)
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
    private List<Category> saveCategories(List<Category> categories, Restaurant restaurant) {
        return restaurantCategoryPersistenceMapper.mapToJpaEntities(categories, restaurant)
                .stream()
                .map(restaurantCategoryRepository::save)
                .map(entity -> categoryPersistenceMapper.mapToDomainEntity(entity.getCategory()))
                .collect(Collectors.toList());
    }

    /**
     * Get page or the default one
     * <p>
     * The default page is sorted by 'rating' property!
     *
     * @param page page
     * @return page containing sorting or page with the default one
     */
    private Pageable getPageOrDefault(Pageable page) {
        Sort sort = page.getSortOr(Sort.by(Sort.Direction.DESC, "rating"));
        return PageRequest.of(page.getPageNumber(), page.getPageSize(), sort);
    }

}

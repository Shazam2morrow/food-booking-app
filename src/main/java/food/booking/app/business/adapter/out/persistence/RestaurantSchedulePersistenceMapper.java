package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.in.restaurant.exception.RestaurantNotFoundException;
import food.booking.app.business.domain.Restaurant;
import food.booking.app.shared.domain.DailySchedule;
import food.booking.app.shared.domain.Time;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Schedule persistence mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class RestaurantSchedulePersistenceMapper {

    private final RestaurantPersistenceResolver restaurantPersistenceResolver;

    /**
     * Map to restaurant schedule entity
     *
     * @param dailySchedule daily schedule
     * @param restaurant    restaurant
     * @return restaurant schedule entity
     * @throws RestaurantNotFoundException if restaurant was not found
     */
    RestaurantScheduleJpaEntity mapToJpaEntity(DailySchedule dailySchedule, Restaurant restaurant) {
        return new RestaurantScheduleJpaEntity(
                restaurantPersistenceResolver.resolve(restaurant),
                dailySchedule.getStartWeekDay(),
                dailySchedule.getEndWeekDay(),
                dailySchedule.getStartAt().getHour(),
                dailySchedule.getStartAt().getMinute(),
                dailySchedule.getEndAt().getHour(),
                dailySchedule.getEndAt().getMinute());
    }

    /**
     * Map to restaurant schedule entities
     *
     * @param dailySchedules daily schedules
     * @param restaurant     restaurant
     * @return restaurant schedule entitities
     * @throws RestaurantNotFoundException if restaurant was not found
     */
    List<RestaurantScheduleJpaEntity> mapToJpaEntities(List<DailySchedule> dailySchedules, Restaurant restaurant) {
        return Objects.isNull(dailySchedules)
                ? null : dailySchedules.stream()
                .map(dailySchedule -> mapToJpaEntity(dailySchedule, restaurant))
                .sorted(RestaurantScheduleJpaEntity.getComparator())
                .collect(Collectors.toList());
    }

    /**
     * Map to daily schedule
     *
     * @param entity entity
     * @return daily schedule
     */
    DailySchedule mapToDomainEntity(RestaurantScheduleJpaEntity entity) {
        return new DailySchedule(
                entity.getStartWeekDay(),
                new Time(entity.getStartAtHour(), entity.getStartAtMinute()),
                entity.getEndWeekDay(),
                new Time(entity.getEndAtHour(), entity.getEndAtMinute()));
    }

}

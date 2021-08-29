package food.booking.app.business.adapter.out.persistence;

import food.booking.app.shared.domain.DailySchedule;
import food.booking.app.shared.domain.Time;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Schedule persistence mapper
 *
 * @author shazam2morrow
 */
class RestaurantSchedulePersistenceMapper {

    RestaurantScheduleJpaEntity mapToJpaEntity(DailySchedule dailySchedule) {
        return new RestaurantScheduleJpaEntity(
                null,
                dailySchedule.getStartWeekDay(),
                dailySchedule.getEndWeekDay(),
                dailySchedule.getStartAt().getHour(),
                dailySchedule.getStartAt().getMinute(),
                dailySchedule.getEndAt().getHour(),
                dailySchedule.getEndAt().getMinute());
    }

    RestaurantScheduleJpaEntity mapToJpaEntity(DailySchedule dailySchedule, RestaurantJpaEntity restaurant) {
        return new RestaurantScheduleJpaEntity(
                restaurant,
                dailySchedule.getStartWeekDay(),
                dailySchedule.getEndWeekDay(),
                dailySchedule.getStartAt().getHour(),
                dailySchedule.getStartAt().getMinute(),
                dailySchedule.getEndAt().getHour(),
                dailySchedule.getEndAt().getMinute());
    }

    DailySchedule mapToDomainEntity(RestaurantScheduleJpaEntity entity) {
        return new DailySchedule(
                entity.getStartWeekDay(),
                new Time(entity.getStartAtHour(), entity.getStartAtMinute()),
                entity.getEndWeekDay(),
                new Time(entity.getEndAtHour(), entity.getEndAtMinute()));
    }

    List<RestaurantScheduleJpaEntity> mapToJpaEntities(Collection<DailySchedule> dailySchedules) {
        if (Objects.isNull(dailySchedules)) {
            return new ArrayList<>();
        }
        return dailySchedules.stream()
                .map(this::mapToJpaEntity)
                .sorted(RestaurantScheduleJpaEntity.getComparator())
                .collect(Collectors.toList());
    }

    List<RestaurantScheduleJpaEntity> mapToJpaEntities(List<DailySchedule> dailySchedules, RestaurantJpaEntity restaurant) {
        return Objects.isNull(dailySchedules)
                ? null : dailySchedules.stream()
                .map(dailySchedule -> mapToJpaEntity(dailySchedule, restaurant))
                .sorted(RestaurantScheduleJpaEntity.getComparator())
                .collect(Collectors.toList());
    }

    List<DailySchedule> mapToDomainEntities(Collection<RestaurantScheduleJpaEntity> entities) {
        if (Objects.isNull(entities)) {
            return new ArrayList<>();
        }
        return entities.stream().map(this::mapToDomainEntity).collect(Collectors.toList());
    }

}

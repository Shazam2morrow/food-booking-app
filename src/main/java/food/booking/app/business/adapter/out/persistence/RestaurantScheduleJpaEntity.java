package food.booking.app.business.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Comparator;

/**
 * Schedule JPA entity
 *
 * @author shazam2morrow
 */
@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "restaurant_schedule", uniqueConstraints = {
        @UniqueConstraint(name = "restaurant_schedule_uk", columnNames = {
                "restaurant_id",
                "start_week_day",
                "end_week_day"})
})
class RestaurantScheduleJpaEntity {

    /**
     * Primary identifier
     */
    @Id
    @Getter
    @NotNull(message = "id can not be null")
    @Positive(message = "id can not be negative or zero")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_schedule_seq_gen")
    @SequenceGenerator(name = "restaurant_schedule_seq_gen", sequenceName = "restaurant_schedule_seq", allocationSize = 1)
    private Long id;

    /**
     * Restaurant
     */
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "restaurant_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "restaurant_schedule_restaurantId_fk")
    )
    private RestaurantJpaEntity restaurant;

    /**
     * Start week day
     */
    @Getter
    @Setter
    @Column(name = "start_week_day", nullable = false)
    @NotNull(message = "startWeekDay can not be null")
    @Type(type = "food.booking.app.business.adapter.out.persistence.WeekDayUserType")
    private WeekDay startWeekDay;

    /**
     * End week day
     */
    @Getter
    @Setter
    @Column(name = "end_week_day", nullable = false)
    @NotNull(message = "startWeekDay can not be null")
    @Type(type = "food.booking.app.business.adapter.out.persistence.WeekDayUserType")
    private WeekDay endWeekDay;

    /**
     * Start at hour
     */
    @Getter
    @Setter
    @Column(name = "start_at_hour", nullable = false)
    @NotNull(message = "startAtHour can not be null")
    @PositiveOrZero(message = "startAtHour must not be negative")
    private Short startAtHour;

    /**
     * Start at minute
     */
    @Getter
    @Setter
    @Column(name = "start_at_minute", nullable = false)
    @NotNull(message = "startAtMinute can not be null")
    @PositiveOrZero(message = "startAtMinute must not be negative")
    private Short startAtMinute;

    /**
     * End at hour
     */
    @Getter
    @Setter
    @Column(name = "end_at_hour", nullable = false)
    @NotNull(message = "endAtHour can not be null")
    @PositiveOrZero(message = "endAtHour must not be negative")
    private Short endAtHour;

    /**
     * End at minute
     */
    @Getter
    @Setter
    @Column(name = "end_at_minute", nullable = false)
    @NotNull(message = "endAtMinute can not be null")
    @PositiveOrZero(message = "endAtMinute must not be negative")
    private Short endAtMinute;

    RestaurantScheduleJpaEntity(RestaurantJpaEntity restaurant,
                                WeekDay startWeekDay,
                                WeekDay endWeekDay,
                                Short startAtHour,
                                Short startAtMinute,
                                Short endAtHour,
                                Short endAtMinute) {
        this.endAtHour = endAtHour;
        this.restaurant = restaurant;
        this.endWeekDay = endWeekDay;
        this.startAtHour = startAtHour;
        this.endAtMinute = endAtMinute;
        this.startWeekDay = startWeekDay;
        this.startAtMinute = startAtMinute;
    }

    public static Comparator<RestaurantScheduleJpaEntity> getComparator() {
        return Comparator.comparingInt(schedule -> schedule.getStartWeekDay().getId());
    }

}

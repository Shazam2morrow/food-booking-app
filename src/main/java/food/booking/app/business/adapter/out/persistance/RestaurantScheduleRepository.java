package food.booking.app.business.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Restaurant schedule JPA repository
 *
 * @author shazam2morrow
 */
@Repository
@Transactional(readOnly = true)
interface RestaurantScheduleRepository extends JpaRepository<RestaurantScheduleJpaEntity, Long> {

    @Query(value = "from RestaurantScheduleJpaEntity rsje where rsje.restaurant = :restaurant order by rsje.startWeekDay")
    List<RestaurantScheduleJpaEntity> findAllByRestaurantOrderByStartWeekDay(@Param("restaurant") RestaurantJpaEntity restaurant);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "delete from RestaurantScheduleJpaEntity rsje where rsje.restaurant = :restaurant")
    void deleteByRestaurant(@Param("restaurant") RestaurantJpaEntity restaurant);

}

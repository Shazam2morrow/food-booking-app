package food.booking.app.business.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Restaurant category JPA repository
 *
 * @author shazam2morrow
 */
@Repository
@Transactional(readOnly = true)
interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategoryJpaEntity, Long> {

    @Query(value = "from RestaurantCategoryJpaEntity rcje join fetch rcje.category c where rcje.restaurant = :restaurant order by c.sortOrder")
    List<RestaurantCategoryJpaEntity> findAllByRestaurantOrderBySortOrder(@Param("restaurant") RestaurantJpaEntity restaurant);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "delete from RestaurantCategoryJpaEntity rcje where rcje.restaurant = :restaurant")
    void deleteByRestaurant(@Param("restaurant") RestaurantJpaEntity restaurant);

}

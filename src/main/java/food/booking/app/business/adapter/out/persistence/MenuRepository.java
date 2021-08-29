package food.booking.app.business.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Menu JPA repository
 *
 * @author shazam2morrow
 */
@Repository
@Transactional(readOnly = true)
interface MenuRepository extends JpaRepository<MenuJpaEntity, Long> {

    boolean existsBySlug(String slug);

    Optional<MenuJpaEntity> findBySlug(String slug);

    @Query(value = "from MenuJpaEntity mje inner join mje.restaurant r where r.slug = :restaurantSlug order by mje.sortOrder")
    List<MenuJpaEntity> findAllByRestaurantSlugAndOrderBySortOrder(@Param("restaurantSlug") String restaurantSlug);

}

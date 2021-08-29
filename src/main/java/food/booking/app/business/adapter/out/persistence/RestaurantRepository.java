package food.booking.app.business.adapter.out.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Restaurant JPA repository
 *
 * @author shazam2morrow
 */
@Repository
@Transactional(readOnly = true)
interface RestaurantRepository extends CrudRepository<RestaurantJpaEntity, Long> {

    boolean existsBySlug(String slug);

    Slice<RestaurantJpaEntity> findAll(Pageable page);

    Optional<RestaurantJpaEntity> findBySlug(String slug);

}

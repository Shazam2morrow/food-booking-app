package food.booking.app.business.adapter.out.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Item JPA repository
 *
 * @author shazam2morrow
 */
@Repository
@Transactional(readOnly = true)
interface ItemRepository extends JpaRepository<ItemJpaEntity, Long> {

    boolean existsBySlug(String slug);

    Optional<ItemJpaEntity> findBySlug(String slug);

    @Query(value = "select ije from ItemJpaEntity ije inner join ije.group g where g.slug = :groupSlug order by ije.sortOrder")
    Slice<ItemJpaEntity> findAllByGroupSlugAndOrderBySortOrder(@Param("groupSlug") String groupSlug, Pageable page);

}

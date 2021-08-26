package food.booking.app.business.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Group JPA repository
 *
 * @author shazam2morrow
 */
@Repository
@Transactional(readOnly = true)
interface GroupRepository extends JpaRepository<GroupJpaEntity, Long> {

    boolean existsBySlug(String slug);

    Optional<GroupJpaEntity> findBySlug(String slug);

    @Query(value = "from GroupJpaEntity gje inner join gje.menu m where m.slug = :menuSlug order by gje.sortOrder")
    List<GroupJpaEntity> findAllByMenuSlugAndOrderBySortOrder(@Param("menuSlug") String menuSlug);

}

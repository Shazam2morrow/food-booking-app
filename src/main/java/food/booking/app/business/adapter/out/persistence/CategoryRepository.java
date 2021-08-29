package food.booking.app.business.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Category JPA repository
 *
 * @author shazam2morrow
 */
@Repository
@Transactional(readOnly = true)
interface CategoryRepository extends JpaRepository<CategoryJpaEntity, Long> {

    boolean existsBySlug(String slug);

    Optional<CategoryJpaEntity> findBySlug(String slug);

    @Override
    @Query(value = "from CategoryJpaEntity cje order by cje.sortOrder")
    List<CategoryJpaEntity> findAll();

}

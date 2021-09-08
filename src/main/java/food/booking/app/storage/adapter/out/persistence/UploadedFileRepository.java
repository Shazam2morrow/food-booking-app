package food.booking.app.storage.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.Instant;
import java.util.Optional;

/**
 * Uploaded file JPA repository
 *
 * @author shazam2morrow
 */
@Repository
@Transactional(readOnly = true)
interface UploadedFileRepository extends JpaRepository<UploadedFileJpaEntity, Long> {

    boolean existsBySlug(String slug);

    boolean existsByUrlAndDeletedFalse(URI url);

    Optional<UploadedFileJpaEntity> findBySlug(String slug);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "update UploadedFileJpaEntity ufje " +
            "set ufje.deleted = true, ufje.deletedAt = :deletedAt " +
            "where ufje.slug = :slug")
    void markAsDeletedBySlug(@Param("slug") String slug, @Param("deletedAt") Instant deletedAt);

}

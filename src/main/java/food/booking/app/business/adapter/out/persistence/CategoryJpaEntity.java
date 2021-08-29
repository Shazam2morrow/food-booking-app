package food.booking.app.business.adapter.out.persistence;

import food.booking.app.shared.persistence.UriPersistenceConverter;
import food.booking.app.shared.size.SlugSize;
import food.booking.app.shared.size.UrlSize;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.time.Instant;
import java.util.Objects;

/**
 * Category JPA entity
 *
 * @author shazam2morrow
 */
@Entity
@DynamicUpdate
@EntityListeners({AuditingEntityListener.class})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category", uniqueConstraints = {
        @UniqueConstraint(name = "category_slug_uk", columnNames = {"slug"})
})
class CategoryJpaEntity {

    /**
     * Primary identifier
     */
    @Id
    @Getter
    @NotNull(message = "id can not be null")
    @Positive(message = "id can not be negative or zero")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq_gen")
    @SequenceGenerator(name = "category_seq_gen", sequenceName = "category_seq", allocationSize = 1)
    private Long id;

    /**
     * Slug
     * <p>
     * Category unique slug
     * <p>
     * Example: _TfwvKZm0X-cR-rMde3DCQqP0N4~wSTx
     */
    @Getter
    @Column(
            name = "slug",
            unique = true,
            nullable = false,
            updatable = false,
            length = SlugSize.MAX)
    private String slug;

    /**
     * Title
     * <p>
     * Category title
     * <p>
     * Example: Sushi
     */
    @Setter
    @Getter
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Sort order
     * <p>
     * Category sorting order
     * <p>
     * Example: 100
     * <p>
     * Default: 0
     */
    @Setter
    @Getter
    @Column(name = "sort_order", nullable = false)
    private Short sortOrder;

    /**
     * Active
     * <p>
     * Is category active?
     * <p>
     * Example: true
     * <p>
     * Default: false
     */
    @Setter
    @Column(name = "is_active", nullable = false)
    private Boolean active;

    /**
     * Creation timestamp
     * <p>
     * Category creation timestamp
     * <p>
     * Example: 2021-08-03 18:54:06.056721Z
     * <p>
     * Default: now
     */
    @Getter
    @CreatedDate
    @NotNull(message = "createdAt can not be null")
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    /**
     * Modification timestamp
     * <p>
     * Category modification timestamp
     * <p>
     * Example: 2021-08-03 19:02:29.294535Z
     * <p>
     * Default: now
     */
    @Getter
    @LastModifiedDate
    @NotNull(message = "updatedAt can not be null")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    /**
     * Icon URL
     * <p>
     * Category icon URL
     * <p>
     * Example: https://example.com/storage/-f1GqM0xR2yj3l_X6Z.tW9dDIH2Dzw1B
     * <p>
     * Default: null
     */
    @Setter
    @Getter
    @Nullable
    @Column(name = "icon_url", length = UrlSize.MAX)
    @Convert(converter = UriPersistenceConverter.class)
    private URI iconUrl;

    CategoryJpaEntity(String slug, String title, @Nullable Short sortOrder, @Nullable URI iconUrl) {
        this.slug = slug;
        this.title = title;
        this.iconUrl = iconUrl;
        this.active = Boolean.FALSE;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.sortOrder = Objects.isNull(sortOrder) ? 0 : sortOrder;
    }

    Boolean isActive() {
        return active;
    }

}

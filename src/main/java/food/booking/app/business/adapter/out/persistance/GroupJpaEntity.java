package food.booking.app.business.adapter.out.persistance;

import food.booking.app.shared.persistance.UriPersistanceConverter;
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
 * Group JPA entity
 *
 * @author shazam2morrow
 */
@Entity
@DynamicUpdate
@EntityListeners({AuditingEntityListener.class})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "menu_group", uniqueConstraints = {
        @UniqueConstraint(name = "menu_group_slug_uk", columnNames = {"slug"}),
})
class GroupJpaEntity {

    /**
     * Primary key
     * <p>
     * Example: 1
     */
    @Id
    @NotNull(message = "id can not be null")
    @Positive(message = "id must be greater than zero")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_group_seq_gen")
    @SequenceGenerator(name = "menu_group_seq_gen", sequenceName = "menu_group_seq", allocationSize = 1)
    private Long id;

    /**
     * Menu
     */
    @Getter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "menu_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "menu_group_menuId_fk"))
    private MenuJpaEntity menu;

    /**
     * Slug
     * <p>
     * Group slug
     * <p>
     * Example: JXhdc7pESkHAh65TDLm9tDkV4Q9azrBd
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
     * Group title
     * <p>
     * Example: bevarages
     */
    @Getter
    @Setter
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Sort order
     * <p>
     * Group sort order
     * <p>
     * Example: 100
     * <p>
     * Default: 0
     */
    @Getter
    @Setter
    @Column(name = "sort_order", nullable = false)
    private Short sortOrder;

    /**
     * Activation state
     * <p>
     * Group activation state
     * <p>
     * For example: true
     * <p>
     * Default: false
     */
    @Getter
    @Setter
    @Column(name = "is_active", nullable = false)
    private Boolean active;

    /**
     * Creation timestamp
     * <p>
     * Group creation timestamp
     * <p>
     * For example: 2021-08-03 18:54:06.056721Z
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
     * Group modification timestamp
     * <p>
     * For example: 2021-08-03 19:02:29.294535Z
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
     * Group icon URL
     * <p>
     * For example: https://example.com/storage/-f1GqM0xR2yj3l_X6Z.tW9dDIH2Dzw1B
     * <p>
     * Default: null
     */
    @Getter
    @Setter
    @Column(name = "icon_url", length = UrlSize.MAX)
    @Convert(converter = UriPersistanceConverter.class)
    private URI iconUrl;

    GroupJpaEntity(MenuJpaEntity menu, String slug, String title, @Nullable Short sortOrder, @Nullable URI iconURl) {
        this.menu = menu;
        this.slug = slug;
        this.title = title;
        this.iconUrl = iconURl;
        this.active = Boolean.FALSE;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.sortOrder = Objects.isNull(sortOrder) ? 0 : sortOrder;
    }

    Boolean isActive() {
        return active;
    }

}

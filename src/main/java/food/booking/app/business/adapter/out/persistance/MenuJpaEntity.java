package food.booking.app.business.adapter.out.persistance;

import food.booking.app.shared.size.SlugSize;
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
import java.time.Instant;
import java.util.Objects;

/**
 * Menu JPA entity
 *
 * @author shazam2morrow
 */
@Entity
@DynamicUpdate
@EntityListeners({AuditingEntityListener.class})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "restaurant_menu", uniqueConstraints = {
        @UniqueConstraint(name = "restaurant_menu_slug_uk", columnNames = {"slug"})
})
class MenuJpaEntity {

    /**
     * Primary identifier
     * <p>
     * Example: 1
     */
    @Id
    @NotNull(message = "id can not be null")
    @Positive(message = "id must be greater than zero")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_menu_seq_gen")
    @SequenceGenerator(name = "restaurant_menu_seq_gen", sequenceName = "restaurant_menu_seq", allocationSize = 1)
    private Long id;

    /**
     * Restaurant
     */
    @Getter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "restaurant_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "restaurant_menu_restaurantId_fk")
    )
    private RestaurantJpaEntity restaurant;

    /**
     * Slug
     * <p>
     * Menu slug
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
     * Menu title
     * <p>
     * For example: Chibo Sano
     */
    @Setter
    @Getter
    @Column(name = "title")
    private String title;

    /**
     * Sort order
     * <p>
     * Menu ordering
     * <p>
     * For example: 1
     * <p>
     * Default: 0
     */
    @Setter
    @Getter
    @Column(name = "sort_order")
    private Short sortOrder;

    /**
     * Activation status
     * <p>
     * Is menu active?
     * <p>
     * For example: true
     * <p>
     * Default: false
     */
    @Setter
    @Getter
    @Column(name = "is_active")
    private Boolean active;

    /**
     * Creation timestamp
     * <p>
     * Menu creation timestamp
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
     * Menu modification timestamp
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

    MenuJpaEntity(RestaurantJpaEntity restaurant, String slug, String title, @Nullable Short sortOrder) {
        this.slug = slug;
        this.title = title;
        this.active = Boolean.FALSE;
        this.restaurant = restaurant;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.sortOrder = Objects.isNull(sortOrder) ? 0 : sortOrder;
    }

    Boolean isActive() {
        return active;
    }

}

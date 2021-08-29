package food.booking.app.business.adapter.out.persistence;

import food.booking.app.shared.persistence.UriPersistenceConverter;
import food.booking.app.shared.size.DescriptionSize;
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
import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.Objects;

/**
 * Item JPA entity
 *
 * @author shazam2morrow
 */
@Entity
@DynamicUpdate
@EntityListeners({AuditingEntityListener.class})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "menu_item", uniqueConstraints = {
        @UniqueConstraint(name = "menu_item_slug_uk", columnNames = {"slug"})
})
class ItemJpaEntity {

    /**
     * Primary key
     * <p>
     * Example: 1
     */
    @Id
    @NotNull(message = "id can not be null")
    @Positive(message = "id must be greater than zero")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_item_seq_gen")
    @SequenceGenerator(name = "menu_item_seq_gen", sequenceName = "menu_item_seq", allocationSize = 1)
    private Long id;

    /**
     * Group
     */
    @Getter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "group_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "menu_item_groupId_fk")
    )
    private GroupJpaEntity group;

    /**
     * Slug
     * <p>
     * Item slug
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
     * Item title
     * <p>
     * Example: Burger Happy Meal
     */
    @Getter
    @Setter
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Calories
     * <p>
     * Item calories
     * <p>
     * Example: 4560
     * <p>
     * Default value: 0
     */
    @Getter
    @Setter
    @Column(name = "calories", nullable = false)
    private Integer calories;

    /**
     * Sort order
     * <p>
     * Item sorting order
     * <p>
     * Example: 50
     * <p>
     * Default value: 0
     */
    @Getter
    @Setter
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    /**
     * Cooking time
     * <p>
     * Item cooking time in minutes
     * <p>
     * Example: 90
     * <p>
     * Default value: 0
     */
    @Getter
    @Setter
    @Column(name = "cooking_time", nullable = false)
    private Short cookingTime;

    /**
     * Price
     * <p>
     * Menu item price
     * <p>
     * Example: 1500.0000
     * <p>
     * Default value: 0
     */
    @Getter
    @Setter
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    /**
     * Is active?
     * <p>
     * Menu item activation state
     * <p>
     * Example: true
     * <p>
     * Default value: false
     */
    @Setter
    @Column(name = "is_active", nullable = false)
    private Boolean active;

    /**
     * Creation timestamp
     * <p>
     * Menu item creation timestamp
     * <p>
     * Example: 2021-08-03 18:54:06.056721Z
     */
    @Getter
    @CreatedDate
    @NotNull(message = "createdAt can not be null")
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    /**
     * Modification timestamp
     * <p>
     * Menu item modification timestamp
     * <p>
     * Example: 2021-08-03 19:02:29.294535Z
     */
    @Getter
    @LastModifiedDate
    @NotNull(message = "updatedAt can not be null")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    /**
     * Banner URL
     * <p>
     * Item banner URL
     * <p>
     * Example: https://example.com/storage/-f1GqM0xR2yj3l_X6Z.tW9dDIH2Dzw1B
     * <p>
     * Defautl: null
     */
    @Getter
    @Setter
    @Nullable
    @Column(name = "banner_url", length = UrlSize.MAX)
    @Convert(converter = UriPersistenceConverter.class)
    private URI bannerUrl;

    /**
     * Description
     * <p>
     * Menu item description
     * <p>
     * Example: Lorem ipsum dolor sit amet, consectetur adipiscing elit,
     * sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
     * Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat
     */
    @Getter
    @Setter
    @Nullable
    @Column(name = "description", length = DescriptionSize.MAX)
    private String description;

    ItemJpaEntity(
            String slug,
            String title,
            GroupJpaEntity group,
            @Nullable URI bannerUrl,
            @Nullable BigDecimal price,
            @Nullable Integer calories,
            @Nullable Integer sortOrder,
            @Nullable Short cookingTime,
            @Nullable String description) {
        this.slug = slug;
        this.group = group;
        this.title = title;
        this.bannerUrl = bannerUrl;
        this.active = Boolean.FALSE;
        this.description = description;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.calories = Objects.isNull(calories) ? 0 : calories;
        this.sortOrder = Objects.isNull(sortOrder) ? 0 : sortOrder;
        this.price = Objects.isNull(price) ? BigDecimal.ZERO : price;
        this.cookingTime = Objects.isNull(cookingTime) ? 0 : cookingTime;
    }

    Boolean isActive() {
        return active;
    }

}

package food.booking.app.business.adapter.out.persistence;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import food.booking.app.shared.Slugable;
import food.booking.app.shared.persistence.UriPersistenceConverter;
import food.booking.app.shared.size.DescriptionSize;
import food.booking.app.shared.size.ShortTitleSize;
import food.booking.app.shared.size.SlugSize;
import food.booking.app.shared.size.UrlSize;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.locationtech.jts.geom.Point;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Restaurant JPA entity
 *
 * @author shazam2morrow
 */
@Entity
@DynamicUpdate
@EntityListeners({AuditingEntityListener.class})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "restaurant", uniqueConstraints = {
        @UniqueConstraint(name = "restaurant_slug_uk", columnNames = {"slug"})
})
class RestaurantJpaEntity implements Slugable<String> {

    /**
     * Primary identifier
     */
    @Id
    @NotNull(message = "id can not be null")
    @Positive(message = "id must be greater than zero")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq_gen")
    @SequenceGenerator(name = "restaurant_seq_gen", sequenceName = "restaurant_seq", allocationSize = 1)
    private Long id;

    /**
     * Type
     * <p>
     * Restaurant type
     * <p>
     * Example: {@link RestaurantType#RESTAURANT}
     */
    @Getter
    @Column(name = "type", nullable = false, updatable = false)
    @Type(type = "food.booking.app.business.adapter.out.persistence.RestaurantUserType")
    private RestaurantType type;

    /**
     * Slug
     * <p>
     * Restaurant unique slug
     * <p>
     * Example: _TfwvKZm0X-cR-rMde3DCQqP0N4zwSTx
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
     * Restaurant title
     * <p>
     * Example: Chibo Sano
     */
    @Setter
    @Getter
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Address
     * <p>
     * Restaurant address
     * <p>
     * Example: 221 Baker Street
     */
    @Setter
    @Getter
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * Rating
     * <p>
     * Restaurant rating (0 - 100) in percents
     * <p>
     * Example: 80
     * <p>
     * Default: 0
     */
    @Setter
    @Getter
    @Column(name = "rating")
    private Short rating;

    /**
     * Creation timestamp
     * <p>
     * Restaurant creation timestamp
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
     * Restaurant modification timestamp
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
     * Active
     * <p>
     * Is restaurant active?
     * <p>
     * Example: true
     * <p>
     * Default: false
     */
    @Setter
    @Column(name = "is_active", nullable = false)
    private Boolean active;

    /**
     * Average receipt
     * <p>
     * Restaurant average receipt
     * <p>
     * Example: 23.4923
     * <p>
     * Default: 0
     */
    @Setter
    @Getter
    @Column(name = "average_receipt")
    private BigDecimal averageReceipt;

    /**
     * Aliases
     * <p>
     * Restaurant aliases
     * <p>
     * Example: ["ninja", "japan", "tokio"]
     * <p>
     * Default: empty
     */
    @Setter
    @Getter
    @Type(type = "list-array")
    @Column(name = "aliases", columnDefinition = "text[]")
    private List<String> aliases;

    /**
     * Images
     * <p>
     * Restaurant media
     * <p>
     * Example: ["https://example.com/storage/-f1GqM0xR2yj3l_X6Z.tW9dDIH2Dzw1B"]
     * <p>
     * Default: empty
     */
    @Setter
    @Getter
    @Type(type = "list-array")
    @Column(name = "images", columnDefinition = "text[]")
    private List<String> images;

    /**
     * Media
     * <p>
     * Restaurant media
     * <p>
     * Serialized content of `media` field
     */
    @Setter
    @Getter
    @Transient
    private List<URI> media;

    /**
     * Location
     * <p>
     * Restaurant location (longitude, latitude)
     * <p>
     * Example: (24.55432, 99.22234)
     * <p>
     * Default: null
     */
    @Setter
    @Getter
    @Nullable
    @Column(name = "location")
    private Point location;

    /**
     * Short title
     * <p>
     * Restaurant short title
     * <p>
     * Example: chibo
     * <p>
     * Default: null
     */
    @Setter
    @Getter
    @Nullable
    @Column(name = "short_title", length = ShortTitleSize.MAX)
    private String shortTitle;

    /**
     * Banner URL
     * <p>
     * Restaurant banner URL
     * <p>
     * Example: https://example.com/storage/-f1GqM0xR2yj3l_X6Z.tW9dDIH2Dzw1B
     */
    @Setter
    @Getter
    @Nullable
    @Column(name = "banner_url", length = UrlSize.MAX)
    @Convert(converter = UriPersistenceConverter.class)
    private URI bannerUrl;

    /**
     * Description
     * <p>
     * Restaurant description
     * <p>
     * Example: Modern restaurant with europian kitchen
     * <p>
     * Default: null
     */
    @Setter
    @Getter
    @Nullable
    @Column(name = "description", length = DescriptionSize.MAX)
    private String description;

    RestaurantJpaEntity(String slug,
                        String title,
                        String address,
                        RestaurantType type,
                        @Nullable URI bannerUrl,
                        @Nullable Point location,
                        @Nullable List<URI> media,
                        @Nullable String shortTitle,
                        @Nullable String description,
                        @Nullable List<String> aliases,
                        @Nullable BigDecimal averageReceipt) {
        this.rating = 0;
        this.type = type;
        this.slug = slug;
        this.title = title;
        this.address = address;
        this.location = location;
        this.bannerUrl = bannerUrl;
        this.active = Boolean.FALSE;
        this.shortTitle = shortTitle;
        this.description = description;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.media = Objects.isNull(media) ? new ArrayList<>() : media;
        this.aliases = Objects.isNull(aliases) ? new ArrayList<>() : aliases;
        this.averageReceipt = Objects.isNull(averageReceipt) ? BigDecimal.ZERO : averageReceipt;
    }

    Boolean isActive() {
        return active;
    }

    @PreUpdate
    @PrePersist
    void sortFields() {
        sortAliases();
        convertToImage();
    }

    @PostLoad
    void convertFromImage() {
        if (Objects.nonNull(images) && !images.isEmpty()) {
            media = images.stream().map(URI::create).collect(Collectors.toList());
        }
    }

    void convertToImage() {
        if (Objects.nonNull(media) && !media.isEmpty()) {
            images = media.stream()
                    .sorted(Comparator.naturalOrder())
                    .map(URI::toString)
                    .collect(Collectors.toList());
        }
    }

    void sortAliases() {
        if (Objects.nonNull(aliases) && !aliases.isEmpty()) {
            aliases.sort(Comparator.naturalOrder());
        }
    }

}

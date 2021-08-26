package food.booking.app.business.adapter.out.persistance;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Restaurant category JPA entity
 *
 * @author shazam2morrow
 */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "restaurant_category", uniqueConstraints = {
        @UniqueConstraint(name = "restaurant_category_uk", columnNames = {"restaurant_id", "category_id"})
})
class RestaurantCategoryJpaEntity {

    /**
     * Primary identifier
     */
    @Id
    @Getter
    @NotNull(message = "id can not be null")
    @Positive(message = "id can not be negative or zero")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_category_seq_gen")
    @SequenceGenerator(name = "restaurant_category_seq_gen", sequenceName = "restaurant_category_seq", allocationSize = 1)
    private Long id;

    /**
     * Category
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "restaurant_category_categoryId_fk")
    )
    private CategoryJpaEntity category;

    /**
     * Restaurant
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "restaurant_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "restaurant_category_restaurantId_fk")
    )
    private RestaurantJpaEntity restaurant;

}

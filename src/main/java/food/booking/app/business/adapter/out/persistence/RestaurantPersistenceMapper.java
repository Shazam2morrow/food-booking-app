package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.out.restaurant.CreateRestaurant;
import food.booking.app.business.app.port.out.restaurant.UpdateRestaurantDetails;
import food.booking.app.business.domain.Restaurant;
import food.booking.app.shared.domain.mapper.LocationMapper;
import lombok.RequiredArgsConstructor;

/**
 * Restaurant persistence mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class RestaurantPersistenceMapper {

    private final LocationMapper locationMapper;

    private final RestaurantPersistenceResolver restaurantPersistenceResolver;

    /**
     * Map to restaurant jpa entity
     *
     * @param createRestaurant create restaurant
     * @return restaurant jpa entity
     */
    RestaurantJpaEntity mapToJpaEntity(CreateRestaurant createRestaurant) {
        return new RestaurantJpaEntity(
                createRestaurant.slug(),
                createRestaurant.title(),
                createRestaurant.address(),
                createRestaurant.type(),
                createRestaurant.bannerUrl(),
                locationMapper.mapToPoint(createRestaurant.location()),
                createRestaurant.media(),
                createRestaurant.shortTitle(),
                createRestaurant.description(),
                createRestaurant.aliases(),
                createRestaurant.averageReceipt());
    }

    /**
     * Map to restaurant domain entity
     *
     * @param entity restaurant jpa entity
     * @return restaurant domain entity
     */
    Restaurant mapToDomainEntity(RestaurantJpaEntity entity) {
        var restaurant = new Restaurant();
        restaurant.setSlug(entity.getSlug());
        restaurant.setSlug(entity.getSlug());
        restaurant.setType(entity.getType());
        restaurant.setMedia(entity.getMedia());
        restaurant.setTitle(entity.getTitle());
        restaurant.setActive(entity.isActive());
        restaurant.setRating(entity.getRating());
        restaurant.setAddress(entity.getAddress());
        restaurant.setAliases(entity.getAliases());
        restaurant.setAliases(entity.getAliases());
        restaurant.setBannerUrl(entity.getBannerUrl());
        restaurant.setCreatedAt(entity.getCreatedAt());
        restaurant.setUpdatedAt(entity.getUpdatedAt());
        restaurant.setShortTitle(entity.getShortTitle());
        restaurant.setDescription(entity.getDescription());
        restaurant.setAverageReceipt(entity.getAverageReceipt());
        restaurant.setLocation(locationMapper.mapToLocation(entity.getLocation()));
        return restaurant;
    }

    /**
     * Apply updated details
     *
     * @param details update restaurant details
     * @return restaurant with updated details
     */
    Restaurant applyUpdatedDetails(UpdateRestaurantDetails details) {
        RestaurantJpaEntity entity = restaurantPersistenceResolver.resolve(details);
        entity.setTitle(details.title());
        entity.setMedia(details.media());
        entity.setActive(details.active());
        entity.setAddress(details.address());
        entity.setAliases(details.aliases());
        entity.setBannerUrl(details.bannerUrl());
        entity.setShortTitle(details.shortTitle());
        entity.setDescription(details.description());
        entity.setAverageReceipt(details.averageReceipt());
        entity.setLocation(locationMapper.mapToPoint(details.location()));
        return mapToDomainEntity(entity);
    }

}

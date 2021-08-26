package food.booking.app.business.adapter.out.persistance;

import food.booking.app.business.app.port.out.restaurant.CreateRestaurant;
import food.booking.app.business.app.port.out.restaurant.UpdateRestaurantDetails;
import food.booking.app.business.domain.Restaurant;
import food.booking.app.shared.domain.LocationMapper;
import lombok.RequiredArgsConstructor;

/**
 * Restaurant persistance mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class RestaurantPersistanceMapper {

    private final LocationMapper locationMapper;

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

    void applyUpdatedDetails(RestaurantJpaEntity entity, UpdateRestaurantDetails details) {
        entity.setTitle(details.title());
        entity.setActive(details.active());
        entity.setMedia(details.media());
        entity.setAddress(details.address());
        entity.setAliases(details.aliases());
        entity.setBannerUrl(details.bannerUrl());
        entity.setShortTitle(details.shortTitle());
        entity.setDescription(details.description());
        entity.setAverageReceipt(details.averageReceipt());
        entity.setLocation(locationMapper.mapToPoint(details.location()));
    }

}

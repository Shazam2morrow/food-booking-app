package food.booking.app.business.adapter.in.web.restaurant;

import food.booking.app.business.adapter.in.web.category.CategoryModel;
import food.booking.app.business.adapter.in.web.category.CategoryModelAssembler;
import food.booking.app.business.adapter.in.web.menu.MenuModelAssembler;
import food.booking.app.business.domain.Restaurant;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Restaurant model assembler
 *
 * @author shazam2morrow
 */
public class RestaurantModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantModel> {

    private final MenuModelAssembler menuModelAssembler;

    private final CategoryModelAssembler categoryModelAssembler;

    private final static Class<LoadRestaurantDetailsController> DETAILS = LoadRestaurantDetailsController.class;

    public RestaurantModelAssembler(Class<?> controllerClass,
                                    Class<RestaurantModel> resourceType,
                                    MenuModelAssembler menuModelAssembler,
                                    CategoryModelAssembler categoryModelAssembler) {
        super(controllerClass, resourceType);
        this.menuModelAssembler = menuModelAssembler;
        this.categoryModelAssembler = categoryModelAssembler;
    }

    @Override
    public RestaurantModel toModel(Restaurant restaurant) {
        var model = new RestaurantModel();
        model.setSlug(restaurant.getSlug());
        model.setType(restaurant.getType());
        model.setTitle(restaurant.getTitle());
        model.setMedia(restaurant.getMedia());
        model.setActive(restaurant.isActive());
        model.setAddress(restaurant.getAddress());
        model.setAliases(restaurant.getAliases());
        model.setLocation(restaurant.getLocation());
        model.setSchedule(restaurant.getSchedule());
        model.setBannerUrl(restaurant.getBannerUrl());
        model.setShortTitle(restaurant.getShortTitle());
        model.setDescription(restaurant.getDescription());
        model.setAverageReceipt(restaurant.getAverageReceipt());
        return addSelf(model);
    }

    /**
     * Map to HAL model
     *
     * @param restaurant restaurant
     * @return HAL restaurant model
     */
    public RepresentationModel<?> toHalModel(Restaurant restaurant) {
        RestaurantModel model = toModel(restaurant);
        HalModelBuilder builder = HalModelBuilder.halModelOf(model);
        if (restaurant.hasCategories()) {
            List<CategoryModel> categoryModels = restaurant.getCategories()
                    .stream()
                    .map(categoryModelAssembler::toModel)
                    .collect(Collectors.toList());
            builder = builder.embed(categoryModels);
        }
        return builder.build();
    }

    /**
     * Add self link
     *
     * @param model restaurant model
     * @return restaurant model with self link
     */
    private RestaurantModel addSelf(RestaurantModel model) {
        model.add(linkTo(methodOn(DETAILS).loadRestaurantDetails(model.getSlug())).withSelfRel());
        model.add(menuModelAssembler.buildMenusLink("menus", model.getSlug()));
        return model;
    }

}

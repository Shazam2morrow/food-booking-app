package food.booking.app.business.adapter.in.web.restaurant;

import food.booking.app.business.domain.Restaurant;
import food.booking.app.shared.model.SliceMetadata;
import food.booking.app.shared.model.SlicedModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Restaurant preview model assembler
 *
 * @author shazam2morrow
 */
public class RestaurantPreviewModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantPreviewModel> {

    private final static Class<LoadRestaurantsController> SLICE = LoadRestaurantsController.class;

    private final static Class<LoadRestaurantDetailsController> DETAILS = LoadRestaurantDetailsController.class;

    public RestaurantPreviewModelAssembler(Class<?> controllerClass, Class<RestaurantPreviewModel> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public RestaurantPreviewModel toModel(Restaurant restaurant) {
        var model = new RestaurantPreviewModel();
        model.setSlug(restaurant.getSlug());
        model.setAliases(model.getAliases());
        model.setTitle(restaurant.getTitle());
        model.setActive(restaurant.isActive());
        model.setRating(restaurant.getRating());
        model.setBannerUrl(restaurant.getBannerUrl());
        model.setShortTitle(restaurant.getShortTitle());
        model.setAverageReceipt(restaurant.getAverageReceipt());
        return addSelf(model);
    }

    /**
     * Map to sliced model
     *
     * @param slice slice of restaurants
     * @return sliced model of restaurants
     */
    public SlicedModel<RestaurantPreviewModel> toSlicedModel(Slice<Restaurant> slice) {
        CollectionModel<RestaurantPreviewModel> models = super.toCollectionModel(slice.getContent());
        return new SlicedModel<>(models, buildSliceMeta(slice), buildLinks(slice));
    }

    /**
     * Build slice metadata
     *
     * @param slice slice of restaurants
     * @return slice metadata
     */
    private SliceMetadata buildSliceMeta(Slice<Restaurant> slice) {
        SliceMetadata model = new SliceMetadata();
        model.setLast(slice.isLast());
        model.setSize(slice.getSize());
        model.setFirst(slice.isFirst());
        model.setPage(slice.getNumber());
        model.setHasNext(slice.hasNext());
        model.setHasContent(slice.hasContent());
        model.setHasPrevious(slice.hasPrevious());
        model.setNumberOfElements(slice.getNumberOfElements());
        return model;
    }

    /**
     * Build links
     *
     * @param slice slice of restaurants
     * @return list of links
     */
    private List<Link> buildLinks(Slice<Restaurant> slice) {
        List<Link> links = new ArrayList<>();
        if (Objects.nonNull(slice.getPageable())) {
            links.add(buildLink("self", slice.getPageable()));
        }
        if (slice.hasNext()) {
            links.add(buildLink("next", slice.nextPageable()));
        }
        if (slice.hasPrevious()) {
            links.add(buildLink("previous", slice.previousPageable()));
        }
        return links;
    }

    /**
     * Build link
     *
     * @param relation relation
     * @param page     page
     * @return link
     */
    private Link buildLink(String relation, Pageable page) {
        UriComponentsBuilder builder = createSliceBuilder(page);
        String href = buildHref(builder, page);
        return Link.of(href, relation);
    }

    /**
     * Build href
     *
     * @param builder builder
     * @param page    page
     * @return href
     */
    private String buildHref(UriComponentsBuilder builder, Pageable page) {
        UriComponentsBuilder queryParams = builder
                .queryParam("page", page.getPageNumber())
                .queryParam("size", page.getPageSize());
        Sort sort = page.getSort();
        if (Objects.nonNull(sort)) {
            for (Sort.Order order : sort) {
                String value = order.getProperty() + "," + order.getDirection().name().toLowerCase();
                queryParams = queryParams.queryParam("sort", value);
            }
        }
        return builder.encode().toUriString();
    }

    /**
     * Create slice builder
     *
     * @param page page
     * @return slice builder
     */
    private UriComponentsBuilder createSliceBuilder(Pageable page) {
        return linkTo(methodOn(SLICE).loadRestaurants(page)).toUriComponentsBuilder();
    }

    /**
     * Add self link
     *
     * @param model restaurant preview model
     * @return restaurant preview model with self link
     */
    private RestaurantPreviewModel addSelf(RestaurantPreviewModel model) {
        return model.add(linkTo(methodOn(DETAILS).loadRestaurantDetails(model.getSlug())).withSelfRel());
    }

}

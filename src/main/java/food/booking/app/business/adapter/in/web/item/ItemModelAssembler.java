package food.booking.app.business.adapter.in.web.item;

import food.booking.app.business.domain.Item;
import food.booking.app.shared.util.UriBuilderUtil;
import food.booking.app.shared.web.model.SliceMetadata;
import food.booking.app.shared.web.model.SlicedModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
 * Item model assembler
 *
 * @author shazam2morrow
 */
public class ItemModelAssembler extends RepresentationModelAssemblerSupport<Item, ItemModel> {

    private final static Class<LoadItemsController> SLICE = LoadItemsController.class;

    private final static Class<LoadItemDetailsController> DETAILS = LoadItemDetailsController.class;

    public ItemModelAssembler(Class<?> controllerClass, Class<ItemModel> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public ItemModel toModel(Item entity) {
        var model = new ItemModel();
        model.setSlug(entity.getSlug());
        model.setTitle(entity.getTitle());
        model.setPrice(entity.getPrice());
        model.setActive(entity.isActive());
        model.setCalories(entity.getCalories());
        model.setSortOrder(entity.getSortOrder());
        model.setBannerUrl(entity.getBannerUrl());
        model.setCookingTime(entity.getCookingTime());
        model.setDescription(entity.getDescription());
        return addSelfLink(model);
    }

    /**
     * Map to sliced model
     *
     * @param slice slice of restaurants
     * @return sliced model of restaurants
     */
    public SlicedModel<ItemModel> toSlicedModel(Slice<Item> slice, String groupSlug) {
        CollectionModel<ItemModel> models = super.toCollectionModel(slice.getContent());
        return new SlicedModel<>(models, buildSliceMeta(slice), buildLinks(slice, groupSlug));
    }

    /**
     * Build slice metadata
     *
     * @param slice slice of restaurants
     * @return slice metadata
     */
    private SliceMetadata buildSliceMeta(Slice<Item> slice) {
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
    private List<Link> buildLinks(Slice<Item> slice, String groupSlug) {
        List<Link> links = new ArrayList<>();
        if (Objects.nonNull(slice.getPageable())) {
            links.add(buildLink(SlicedModel.SELF, slice.getPageable(), groupSlug));
        }
        if (slice.hasNext()) {
            links.add(buildLink(SlicedModel.NEXT, slice.nextPageable(), groupSlug));
        }
        if (slice.hasPrevious()) {
            links.add(buildLink(SlicedModel.PREVIOUS, slice.previousPageable(), groupSlug));
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
    private Link buildLink(String relation, Pageable page, String groupSlug) {
        UriComponentsBuilder builder = createSliceBuilder(groupSlug, page);
        String href = UriBuilderUtil.buildPagedHref(builder, page);
        return Link.of(href, relation);
    }

    /**
     * Create slice builder
     *
     * @param page page
     * @return slice builder
     */
    private UriComponentsBuilder createSliceBuilder(String groupSlug, Pageable page) {
        return linkTo(methodOn(SLICE).loadItems(groupSlug, page)).toUriComponentsBuilder();
    }

    /**
     * Add self link
     *
     * @param model item model
     * @return item model
     */
    private ItemModel addSelfLink(ItemModel model) {
        return model.add(linkTo(methodOn(DETAILS).loadItemDetails(model.getSlug())).withSelfRel());
    }

    /**
     * Build items link
     *
     * @param relation relation
     * @param menuSlug menu slug
     * @return items link
     */
    public Link buildItemsLink(String relation, String menuSlug) {
        return linkTo(methodOn(SLICE).loadItems(menuSlug, Pageable.unpaged())).withRel(relation);
    }

}

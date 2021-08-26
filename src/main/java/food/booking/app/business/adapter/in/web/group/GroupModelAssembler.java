package food.booking.app.business.adapter.in.web.group;

import food.booking.app.business.adapter.in.web.item.ItemModelAssembler;
import food.booking.app.business.domain.Group;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Group model assembler
 *
 * @author shazam2morrow
 */
public class GroupModelAssembler extends RepresentationModelAssemblerSupport<Group, GroupModel> {

    private final ItemModelAssembler itemModelAssembler;

    private static final Class<LoadGroupsController> LIST = LoadGroupsController.class;

    private static final Class<LoadGroupDetailsController> DETAILS = LoadGroupDetailsController.class;

    public GroupModelAssembler(Class<?> controllerClass,
                               Class<GroupModel> resourceType,
                               ItemModelAssembler itemModelAssembler) {
        super(controllerClass, resourceType);
        this.itemModelAssembler = itemModelAssembler;
    }

    @Override
    public GroupModel toModel(Group entity) {
        var model = new GroupModel();
        model.setSlug(entity.getSlug());
        model.setTitle(entity.getTitle());
        model.setActive(entity.isActive());
        model.setIconUrl(entity.getIconUrl());
        model.setSortOrder(entity.getSortOrder());
        return addSelfLink(model);
    }

    /**
     * Add self link
     *
     * @param model group model
     * @return group model
     */
    private GroupModel addSelfLink(GroupModel model) {
        model.add(linkTo(methodOn(DETAILS).loadGroupDetails(model.getSlug())).withSelfRel());
        model.add(itemModelAssembler.buildItemsLink("items", model.getSlug()));
        return model;
    }

    /**
     * Build groups link
     *
     * @param relation relation
     * @param menuSlug menu slug
     * @return link
     */
    public Link buildGroupsLink(String relation, String menuSlug) {
        return linkTo(methodOn(LIST).loadGroups(menuSlug)).withRel(relation);
    }

}

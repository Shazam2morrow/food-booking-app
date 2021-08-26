package food.booking.app.business.adapter.in.web.menu;

import food.booking.app.business.adapter.in.web.group.GroupModelAssembler;
import food.booking.app.business.domain.Menu;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Menu model assembler
 *
 * @author shazam2morrow
 */
public class MenuModelAssembler extends RepresentationModelAssemblerSupport<Menu, MenuModel> {

    private final GroupModelAssembler groupModelAssembler;

    private final static Class<LoadMenusController> MENUS = LoadMenusController.class;

    private final static Class<LoadMenuDetailsController> DETAILS = LoadMenuDetailsController.class;

    public MenuModelAssembler(Class<?> controllerClass,
                              Class<MenuModel> resourceType,
                              GroupModelAssembler groupModelAssembler) {
        super(controllerClass, resourceType);
        this.groupModelAssembler = groupModelAssembler;
    }

    @Override
    public MenuModel toModel(Menu entity) {
        MenuModel model = new MenuModel();
        model.setSlug(entity.getSlug());
        model.setTitle(entity.getTitle());
        model.setActive(entity.isActive());
        model.setSortOrder(entity.getSortOrder());
        return addSelfLink(model);
    }

    /**
     * Add self link
     *
     * @param model menu model
     * @return menu model
     */
    private MenuModel addSelfLink(MenuModel model) {
        model.add(linkTo(methodOn(DETAILS).loadMenuDetails(model.getSlug())).withSelfRel());
        model.add(groupModelAssembler.buildGroupsLink("groups", model.getSlug()));
        return model;
    }

    /**
     * Build menus link
     *
     * @param relation       relation
     * @param restaurantSlug restaurant slug
     * @return link
     */
    public Link buildMenusLink(String relation, String restaurantSlug) {
        return linkTo(methodOn(MENUS).loadMenus(restaurantSlug)).withRel(relation);
    }

}

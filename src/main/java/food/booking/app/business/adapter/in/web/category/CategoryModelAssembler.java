package food.booking.app.business.adapter.in.web.category;

import food.booking.app.business.domain.Category;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Category model assembler
 *
 * @author shazam2morrow
 */
public class CategoryModelAssembler extends RepresentationModelAssemblerSupport<Category, CategoryModel> {

    private final static Class<LoadCategoriesController> LIST = LoadCategoriesController.class;

    private final static Class<LoadCategoryDetailsController> DETAILS = LoadCategoryDetailsController.class;

    CategoryModelAssembler(Class<?> controller, Class<CategoryModel> type) {
        super(controller, type);
    }

    @Override
    public CategoryModel toModel(Category category) {
        var model = new CategoryModel();
        model.setSlug(category.getSlug());
        model.setTitle(category.getTitle());
        model.setActive(category.isActive());
        model.setIconUrl(category.getIconUrl());
        model.setSortOrder(category.getSortOrder());
        return addSelf(model);
    }

    @Override
    public CollectionModel<CategoryModel> toCollectionModel(Iterable<? extends Category> entities) {
        return addSelf(super.toCollectionModel(entities));
    }

    private CategoryModel addSelf(CategoryModel model) {
        return model.add(linkTo(methodOn(DETAILS).loadCategoryDetails(model.getSlug())).withSelfRel());
    }

    private CollectionModel<CategoryModel> addSelf(CollectionModel<CategoryModel> categoryModels) {
        return categoryModels.add(linkTo(methodOn(LIST).loadCategories()).withSelfRel());
    }

}

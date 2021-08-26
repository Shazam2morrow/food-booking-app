package food.booking.app.business.adapter.in.web.category;

import food.booking.app.business.app.port.in.category.LoadCategoryListUseCase;
import food.booking.app.business.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Load categories controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
class LoadCategoriesController {

    private final CategoryModelAssembler categoryModelAssembler;

    private final LoadCategoryListUseCase loadCategoryListUseCase;

    /**
     * Load categories
     *
     * @return category models
     */
    @GetMapping(produces = {MediaTypes.HAL_JSON_VALUE})
    ResponseEntity<RepresentationModel<?>> loadCategories() {
        log.debug("REST request to load categories");
        return ResponseEntity.ok(buildHalModel());
    }

    /**
     * Build HAL model
     *
     * @return HAL representation model
     */
    private RepresentationModel<?> buildHalModel() {
        List<Category> categories = loadCategoryListUseCase.loadList();
        CollectionModel<CategoryModel> model = categoryModelAssembler.toCollectionModel(categories);
        if (model.getContent().isEmpty()) {
            return HalModelBuilder.emptyHalModel()
                    .embed(Collections.emptyList(), CategoryModel.class)
                    .link(model.getRequiredLink("self"))
                    .build();
        }
        return HalModelBuilder.halModelOf(model).build();
    }

}

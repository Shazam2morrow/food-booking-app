package food.booking.app.business.adapter.in.web.category;

import food.booking.app.business.app.port.in.category.LoadCategoryDetailsUseCase;
import food.booking.app.business.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Load category details controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
class LoadCategoryDetailsController {

    private final CategoryModelAssembler categoryModelAssembler;

    private final LoadCategoryDetailsUseCase loadCategoryDetailsUseCase;

    /**
     * Load category details
     *
     * @param categorySlug category slug
     * @return category model
     */
    @GetMapping(path = "/{categorySlug}", produces = {MediaTypes.HAL_JSON_VALUE})
    ResponseEntity<CategoryModel> loadCategoryDetails(@PathVariable String categorySlug) {
        log.debug("REST request to load category details: {}", categorySlug);
        Category category = loadCategoryDetailsUseCase.loadDetailsBySlug(categorySlug);
        CategoryModel model = categoryModelAssembler.toModel(category);
        return ResponseEntity.ok(model);
    }

}

package food.booking.app.business.adapter.in.web.category;

import food.booking.app.business.app.port.in.category.DeleteCategoryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Delete category controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
class DeleteCategoryController {

    private final DeleteCategoryUseCase deleteCategoryUseCase;

    /**
     * Delete category
     *
     * @param categorySlug category slug
     */
    @DeleteMapping(path = "/{categorySlug}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteCategory(@PathVariable String categorySlug) {
        log.debug("REST request to delete category: {}", categorySlug);
        deleteCategoryUseCase.deleteBySlug(categorySlug);
    }

}

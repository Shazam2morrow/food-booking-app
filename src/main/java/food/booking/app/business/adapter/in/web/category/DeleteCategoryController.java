package food.booking.app.business.adapter.in.web.category;

import food.booking.app.business.app.port.in.category.DeleteCategoryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @return no content
     */
    @DeleteMapping(path = "/{categorySlug}")
    ResponseEntity<Void> deleteCategory(@PathVariable String categorySlug) {
        log.debug("REST request to delete category: {}", categorySlug);
        deleteCategoryUseCase.deleteBySlug(categorySlug);
        return ResponseEntity.noContent().build();
    }

}

package food.booking.app.business.adapter.in.web.category;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import food.booking.app.business.app.port.in.category.LoadCategoryDetailsUseCase;
import food.booking.app.business.app.port.in.category.UpdateCategoryDetailsCommand;
import food.booking.app.business.app.port.in.category.UpdateCategoryDetailsUseCase;
import food.booking.app.business.domain.Category;
import food.booking.app.shared.PatchApplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Update category details controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequestMapping("/categories")
class UpdateCategoryDetailsController extends PatchApplier<Category, UpdateCategoryDetailsCommand> {

    private final LoadCategoryDetailsUseCase loadCategoryDetailsUseCase;

    private final UpdateCategoryDetailsUseCase updateCategoryDetailsUseCase;

    UpdateCategoryDetailsController(ObjectMapper objectMapper,
                                    LoadCategoryDetailsUseCase loadCategoryDetailsUseCase,
                                    UpdateCategoryDetailsUseCase updateCategoryDetailsUseCase) {
        super(objectMapper);
        this.loadCategoryDetailsUseCase = loadCategoryDetailsUseCase;
        this.updateCategoryDetailsUseCase = updateCategoryDetailsUseCase;
    }

    /**
     * Update category details
     *
     * @param categorySlug category slug
     * @param patch        json patch
     * @throws JsonPatchException      if patch can not be applied
     * @throws JsonProcessingException if json could not be processed
     */
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PatchMapping(path = "/{categorySlug}", consumes = "application/json-patch+json")
    void updateCategoryDetails(
            @PathVariable String categorySlug,
            @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        log.debug("REST request to update category details: {}, {}", categorySlug, patch);
        Category category = loadCategoryDetailsUseCase.loadDetailsBySlug(categorySlug);
        updateCategoryDetailsUseCase.update(applyPatch(patch, category));
    }

    @Override
    protected Class<UpdateCategoryDetailsCommand> getOutputType() {
        return UpdateCategoryDetailsCommand.class;
    }

}

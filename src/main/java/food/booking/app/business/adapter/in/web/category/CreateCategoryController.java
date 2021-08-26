package food.booking.app.business.adapter.in.web.category;

import food.booking.app.business.app.port.in.category.CreateCategoryCommand;
import food.booking.app.business.app.port.in.category.CreateCategoryUseCase;
import food.booking.app.business.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Create category controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
class CreateCategoryController {

    private final CategoryWebMapper categoryWebMapper;

    private final CreateCategoryUseCase createCategoryUseCase;

    private final CategoryModelAssembler categoryModelAssembler;

    /**
     * Create category
     *
     * @param input create category model
     * @return category model
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaTypes.HAL_JSON_VALUE})
    ResponseEntity<CategoryModel> createCategory(@Valid @RequestBody CreateCategoryModel input) {
        log.debug("REST request to create category: {}", input);
        CreateCategoryCommand command = categoryWebMapper.mapToCommand(input);
        Category category = createCategoryUseCase.create(command);
        CategoryModel model = categoryModelAssembler.toModel(category);
        return model.getLink("self")
                .map(self -> ResponseEntity.created(self.toUri()).body(model))
                .orElseGet(() -> new ResponseEntity<>(model, HttpStatus.CREATED));
    }

}

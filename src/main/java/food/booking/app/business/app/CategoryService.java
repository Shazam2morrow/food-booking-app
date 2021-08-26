package food.booking.app.business.app;

import food.booking.app.business.app.port.in.category.*;
import food.booking.app.business.app.port.out.category.*;
import food.booking.app.business.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Category service
 *
 * @author shazam2morrow
 */
@Slf4j
@RequiredArgsConstructor
class CategoryService implements CreateCategoryUseCase,
        LoadCategoryListUseCase,
        LoadCategoryDetailsUseCase,
        UpdateCategoryDetailsUseCase,
        DeleteCategoryUseCase {

    private final CreateCategoryPort createCategoryPort;

    private final LoadCategoryListPort loadCategoryListPort;

    private final CategoryServiceMapper categoryServiceMapper;

    private final LoadCategoryDetailsPort loadCategoryDetailsPort;

    private final UpdateCategoryDetailsPort updateCategoryDetailsPort;

    @Override
    @Transactional
    public Category create(CreateCategoryCommand command) {
        log.debug("Trying to create category: {}", command);
        CreateCategory createCategory = categoryServiceMapper.mapToCreateCategory(requireValid(command));
        return createCategoryPort.create(createCategory);
    }

    @Override
    @Transactional
    public void update(UpdateCategoryDetailsCommand command) {
        log.debug("Trying to update category details: {}", command);
        UpdateCategoryDetails details = categoryServiceMapper.mapToUpdateCategoryDetails(requireValid(command));
        updateCategoryDetailsPort.update(details);
    }

    @Override
    @Transactional
    public void deleteBySlug(String categorySlug) {
        log.debug("Trying to delete category: {}", categorySlug);
        Category category = loadDetailsBySlug(categorySlug);
        category.setActive(Boolean.FALSE);
        update(categoryServiceMapper.mapToUpdateCategoryDetailsCommand(category));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> loadList() {
        log.debug("Trying to load list of categories");
        return loadCategoryListPort.loadList();
    }

    @Override
    @Transactional(readOnly = true)
    public Category loadDetailsBySlug(String categorySlug) {
        log.debug("Trying to load category details: {}", categorySlug);
        return loadCategoryDetailsPort.loadDetailsBySlug(requireValidSlug(categorySlug));
    }

    /**
     * Validate slug
     *
     * @param slug slug
     * @return validated slug
     */
    private String requireValidSlug(String slug) {
        return Validate.notBlank(slug, "slug can not be blank");
    }

    /**
     * Validate object
     *
     * @param command create category command
     * @return validated object
     */
    private CreateCategoryCommand requireValid(CreateCategoryCommand command) {
        return Validate.notNull(command, "createCategoryCommand can not be null");
    }

    /**
     * Validate object
     *
     * @param command update category details command
     * @return validated object
     */
    private UpdateCategoryDetailsCommand requireValid(UpdateCategoryDetailsCommand command) {
        return Validate.notNull(command, "updateCategoryDetails can not be null");
    }

}

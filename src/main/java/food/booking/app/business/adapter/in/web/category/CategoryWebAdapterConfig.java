package food.booking.app.business.adapter.in.web.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import food.booking.app.business.app.port.in.category.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Category web adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class CategoryWebAdapterConfig {

    @Bean
    CreateCategoryController createCategoryController(CategoryWebMapper categoryWebMapper,
                                                      CreateCategoryUseCase createCategoryUseCase,
                                                      CategoryModelAssembler categoryModelAssembler) {
        return new CreateCategoryController(categoryWebMapper, createCategoryUseCase, categoryModelAssembler);
    }

    @Bean
    LoadCategoryDetailsController loadCategoryDetailsController(
            CategoryModelAssembler categoryModelAssembler,
            LoadCategoryDetailsUseCase loadCategoryDetailsUseCase) {
        return new LoadCategoryDetailsController(categoryModelAssembler, loadCategoryDetailsUseCase);
    }

    @Bean
    UpdateCategoryDetailsController updateCategoryDetailsController(
            ObjectMapper objectMapper,
            LoadCategoryDetailsUseCase loadCategoryDetailsUseCase,
            UpdateCategoryDetailsUseCase updateCategoryDetailsUseCase) {
        return new UpdateCategoryDetailsController(
                objectMapper,
                loadCategoryDetailsUseCase,
                updateCategoryDetailsUseCase);
    }

    @Bean
    LoadCategoriesController loadCategoriesController(CategoryModelAssembler categoryModelAssembler,
                                                      LoadCategoryListUseCase loadCategoryListUseCase) {
        return new LoadCategoriesController(categoryModelAssembler, loadCategoryListUseCase);
    }

    @Bean
    DeleteCategoryController deleteCategoryController(DeleteCategoryUseCase deleteCategoryUseCase) {
        return new DeleteCategoryController(deleteCategoryUseCase);
    }

    @Bean
    CategoryModelAssembler categoryModelAssembler() {
        return new CategoryModelAssembler(LoadCategoryDetailsController.class, CategoryModel.class);
    }

    @Bean
    CategoryWebMapper categoryWebMapper() {
        return new CategoryWebMapper();
    }

}

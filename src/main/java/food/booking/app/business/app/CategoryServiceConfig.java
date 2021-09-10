package food.booking.app.business.app;

import food.booking.app.business.app.port.out.category.*;
import food.booking.app.storage.app.FileUrlResolver;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Category service configuration
 *
 * @author shazam2morrow
 */
@Configuration
class CategoryServiceConfig {

    @Bean
    CategoryService categoryService(CreateCategoryPort createCategoryPort,
                                    LoadCategoryListPort loadCategoryListPort,
                                    CategoryServiceMapper categoryServiceMapper,
                                    LoadCategoryDetailsPort loadCategoryDetailsPort,
                                    UpdateCategoryDetailsPort updateCategoryDetailsPort) {
        return new CategoryService(
                createCategoryPort,
                loadCategoryListPort,
                categoryServiceMapper,
                loadCategoryDetailsPort,
                updateCategoryDetailsPort);
    }

    @Bean
    CategoryServiceMapper categoryServiceMapper(FileUrlResolver fileUrlResolver,
                                                CheckCategoryCanCheckSlugPort checkCategorySlugPort,
                                                RandomStringGenerator randomStringGenerator) {
        return new CategoryServiceMapper(
                checkCategorySlugPort,
                fileUrlResolver,
                randomStringGenerator);
    }

}

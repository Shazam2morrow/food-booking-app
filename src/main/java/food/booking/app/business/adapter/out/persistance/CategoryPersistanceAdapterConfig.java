package food.booking.app.business.adapter.out.persistance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Category persistance adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class CategoryPersistanceAdapterConfig {

    @Bean
    CategorySlugPersistanceAdapter categoryPersistanceAdapter(CategoryRepository categoryRepository,
                                                              CategoryPersistanceMapper categoryPersistanceMapper,
                                                              CategoryPersistanceResolver categoryPersistanceResolver) {
        return new CategorySlugPersistanceAdapter(categoryRepository, categoryPersistanceMapper, categoryPersistanceResolver);
    }

    @Bean
    CategoryPersistanceMapper categoryPersistanceMapper() {
        return new CategoryPersistanceMapper();
    }

}

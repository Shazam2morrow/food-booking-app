package food.booking.app.business.adapter.out.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Category persistence adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class CategoryPersistenceAdapterConfig {

    @Bean
    CategorySlugPersistenceAdapter categorypersistenceAdapter(CategoryRepository categoryRepository,
                                                              CategoryPersistenceMapper categoryPersistenceMapper,
                                                              CategoryPersistenceResolver categoryPersistenceResolver) {
        return new CategorySlugPersistenceAdapter(categoryRepository, categoryPersistenceMapper, categoryPersistenceResolver);
    }

    @Bean
    CategoryPersistenceMapper categorypersistenceMapper() {
        return new CategoryPersistenceMapper();
    }

}

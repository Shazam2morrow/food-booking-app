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
    CategoryPersistenceAdapter categoryPersistenceAdapter(
            CategoryRepository categoryRepository,
            CategoryPersistenceMapper categoryPersistenceMapper,
            CategoryPersistenceResolver categoryPersistenceResolver) {
        return new CategoryPersistenceAdapter(
                categoryRepository,
                categoryPersistenceMapper,
                categoryPersistenceResolver);
    }

    @Bean
    CategoryPersistenceResolver categoryPersistenceResolver(CategoryRepository categoryRepository) {
        return new CategoryPersistenceResolver(categoryRepository);
    }

    @Bean
    CategoryPersistenceMapper categoryPersistenceMapper(CategoryPersistenceResolver categoryPersistenceResolver) {
        return new CategoryPersistenceMapper(categoryPersistenceResolver);
    }

}

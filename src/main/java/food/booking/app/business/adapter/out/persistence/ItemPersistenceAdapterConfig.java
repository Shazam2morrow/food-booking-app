package food.booking.app.business.adapter.out.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Item persistence adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class ItemPersistenceAdapterConfig {

    @Bean
    ItemPersistenceAdapter itemPersistenceAdapter(ItemRepository itemRepository,
                                                  ItemPersistenceMapper itemPersistenceMapper,
                                                  ItemPersistenceResolver itemPersistenceResolver) {
        return new ItemPersistenceAdapter(itemRepository, itemPersistenceMapper, itemPersistenceResolver);
    }

    @Bean
    ItemPersistenceMapper itemPersistenceMapper(ItemPersistenceResolver itemPersistenceResolver,
                                                GroupPersistenceResolver groupPersistenceResolver) {
        return new ItemPersistenceMapper(itemPersistenceResolver, groupPersistenceResolver);
    }

    @Bean
    ItemPersistenceResolver itemPersistenceResolver(ItemRepository itemRepository) {
        return new ItemPersistenceResolver(itemRepository);
    }

}

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
    ItemPersistenceAdapter itempersistenceAdapter(ItemRepository itemRepository,
                                                  ItemPersistenceMapper itemPersistenceMapper,
                                                  ItemPersistenceResolver itemPersistenceResolver) {
        return new ItemPersistenceAdapter(itemRepository, itemPersistenceMapper, itemPersistenceResolver);
    }

    @Bean
    ItemPersistenceMapper itempersistenceMapper(GroupPersistenceResolver groupPersistenceResolver) {
        return new ItemPersistenceMapper(groupPersistenceResolver);
    }

    @Bean
    ItemPersistenceResolver itempersistenceResolver(ItemRepository itemRepository) {
        return new ItemPersistenceResolver(itemRepository);
    }

}

package food.booking.app.business.adapter.out.persistance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Item persistance adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class ItemPersistanceAdapterConfig {

    @Bean
    ItemPersistanceAdapter itemPersistanceAdapter(ItemRepository itemRepository,
                                                  ItemPersistanceMapper itemPersistanceMapper,
                                                  ItemPersistanceResolver itemPersistanceResolver) {
        return new ItemPersistanceAdapter(itemRepository, itemPersistanceMapper, itemPersistanceResolver);
    }

    @Bean
    ItemPersistanceMapper itemPersistanceMapper(GroupPersistanceResolver groupPersistanceResolver) {
        return new ItemPersistanceMapper(groupPersistanceResolver);
    }

    @Bean
    ItemPersistanceResolver itemPersistanceResolver(ItemRepository itemRepository) {
        return new ItemPersistanceResolver(itemRepository);
    }

}

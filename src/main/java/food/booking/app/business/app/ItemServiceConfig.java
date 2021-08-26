package food.booking.app.business.app;

import food.booking.app.business.app.port.out.group.LoadGroupItemSlicePort;
import food.booking.app.business.app.port.out.item.CheckItemSlugPort;
import food.booking.app.business.app.port.out.item.CreateItemPort;
import food.booking.app.business.app.port.out.item.LoadItemDetailsPort;
import food.booking.app.business.app.port.out.item.UpdateItemDetailsPort;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Item service configuration
 *
 * @author shazam2morrow
 */
@Configuration
class ItemServiceConfig {

    @Bean
    ItemService itemService(CreateItemPort createItemPort,
                            ItemServiceMapper itemServiceMapper,
                            LoadItemDetailsPort loadItemDetailsPort,
                            UpdateItemDetailsPort updateItemDetailsPort,
                            LoadGroupItemSlicePort loadGroupItemSlicePort) {
        return new ItemService(
                createItemPort,
                itemServiceMapper,
                loadItemDetailsPort,
                updateItemDetailsPort,
                loadGroupItemSlicePort);
    }

    @Bean
    ItemServiceMapper itemServiceMapper(CheckItemSlugPort checkItemSlugPort,
                                        RandomStringGenerator randomStringGenerator) {
        return new ItemServiceMapper(checkItemSlugPort, randomStringGenerator);
    }

}

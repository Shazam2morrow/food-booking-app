package food.booking.app.business.adapter.in.web.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import food.booking.app.business.adapter.in.web.group.GroupModelAssembler;
import food.booking.app.business.app.port.in.group.LoadGroupItemSliceUseCase;
import food.booking.app.business.app.port.in.item.CreateItemUseCase;
import food.booking.app.business.app.port.in.item.DeleteItemUseCase;
import food.booking.app.business.app.port.in.item.LoadItemDetailsUseCase;
import food.booking.app.business.app.port.in.item.UpdateItemDetailsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Item web adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class ItemWebAdapterConfig {

    @Bean
    CreateItemController createItemController(ItemWebMapper itemWebMapper,
                                              CreateItemUseCase createItemUseCase,
                                              ItemModelAssembler itemModelAssembler,
                                              GroupModelAssembler groupModelAssembler) {
        return new CreateItemController(
                itemWebMapper,
                createItemUseCase,
                itemModelAssembler,
                groupModelAssembler);
    }

    @Bean
    UpdateItemDetailsController updateItemDetailsController(ObjectMapper objectMapper,
                                                            LoadItemDetailsUseCase loadItemDetailsUseCase,
                                                            UpdateItemDetailsUseCase updateItemDetailsUseCase) {
        return new UpdateItemDetailsController(objectMapper, loadItemDetailsUseCase, updateItemDetailsUseCase);
    }

    @Bean
    LoadItemsController loadItemsController(ItemModelAssembler itemModelAssembler,
                                            LoadGroupItemSliceUseCase loadGroupItemSliceUseCase) {
        return new LoadItemsController(itemModelAssembler, loadGroupItemSliceUseCase);
    }

    @Bean
    LoadItemDetailsController loadItemDetailsController(ItemModelAssembler itemModelAssembler,
                                                        LoadItemDetailsUseCase loadItemDetailsUseCase) {
        return new LoadItemDetailsController(itemModelAssembler, loadItemDetailsUseCase);
    }

    @Bean
    DeleteItemController deleteItemController(DeleteItemUseCase deleteItemUseCase) {
        return new DeleteItemController(deleteItemUseCase);
    }

    @Bean
    ItemModelAssembler itemModelAssembler() {
        return new ItemModelAssembler(LoadItemDetailsController.class, ItemModel.class);
    }

    @Bean
    ItemWebMapper menuItemWebMapper() {
        return new ItemWebMapper();
    }

}

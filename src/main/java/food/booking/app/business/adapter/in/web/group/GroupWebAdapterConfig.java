package food.booking.app.business.adapter.in.web.group;

import com.fasterxml.jackson.databind.ObjectMapper;
import food.booking.app.business.adapter.in.web.item.ItemModelAssembler;
import food.booking.app.business.adapter.in.web.menu.MenuModelAssembler;
import food.booking.app.business.app.port.in.group.*;
import food.booking.app.business.app.port.in.menu.LoadMenuDetailsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Group web adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class GroupWebAdapterConfig {

    @Bean
    LoadGroupsController loadGroupsController(MenuModelAssembler menuModelAssembler,
                                              GroupModelAssembler groupModelAssembler,
                                              LoadMenuDetailsUseCase loadMenuDetailsUseCase,
                                              LoadMenuGroupListUseCase loadMenuGroupListUseCase) {
        return new LoadGroupsController(
                menuModelAssembler,
                groupModelAssembler,
                loadMenuDetailsUseCase,
                loadMenuGroupListUseCase);
    }

    @Bean
    CreateGroupController createGroupController(GroupWebMapper groupWebMapper,
                                                CreateGroupUseCase createGroupUseCase,
                                                MenuModelAssembler menuModelAssembler,
                                                GroupModelAssembler groupModelAssembler) {
        return new CreateGroupController(groupWebMapper, createGroupUseCase, menuModelAssembler, groupModelAssembler);
    }

    @Bean
    DeleteGroupController deleteGroupController(DeleteGroupUseCase deleteGroupUseCase) {
        return new DeleteGroupController(deleteGroupUseCase);
    }

    @Bean
    LoadGroupDetailsController loadGroupDetailsController(GroupModelAssembler groupModelAssembler,
                                                          LoadGroupDetailsUseCase loadGroupDetailsUseCase) {
        return new LoadGroupDetailsController(groupModelAssembler, loadGroupDetailsUseCase);
    }

    @Bean
    UpdateGroupDetailsController updateGroupDetailsController(ObjectMapper objectMapper,
                                                              LoadGroupDetailsUseCase loadGroupDetailsUseCase,
                                                              UpdateGroupDetailsUseCase updateGroupDetailsUseCase,
                                                              LocalValidatorFactoryBean localValidatorFactoryBean) {
        return new UpdateGroupDetailsController(
                objectMapper,
                loadGroupDetailsUseCase,
                updateGroupDetailsUseCase,
                localValidatorFactoryBean);
    }

    @Bean
    GroupWebMapper menuGroupWebMapper() {
        return new GroupWebMapper();
    }

    @Bean
    GroupModelAssembler groupModelAssembler(ItemModelAssembler itemModelAssembler) {
        return new GroupModelAssembler(LoadGroupDetailsController.class, GroupModel.class, itemModelAssembler);
    }

}

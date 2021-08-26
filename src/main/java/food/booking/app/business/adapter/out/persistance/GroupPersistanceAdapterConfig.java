package food.booking.app.business.adapter.out.persistance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Group persistance adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class GroupPersistanceAdapterConfig {

    @Bean
    GroupPersistanceAdapter groupPersistanceAdapter(GroupRepository groupRepository,
                                                    GroupPersistanceMapper groupPersistanceMapper,
                                                    GroupPersistanceResolver groupPersistanceResolver) {
        return new GroupPersistanceAdapter(groupRepository, groupPersistanceMapper, groupPersistanceResolver);
    }

    @Bean
    GroupPersistanceMapper groupPersistanceMapper(MenuPersistanceResolver menuPersistanceResolver) {
        return new GroupPersistanceMapper(menuPersistanceResolver);
    }

    @Bean
    GroupPersistanceResolver groupPersistanceResolver(GroupRepository groupRepository) {
        return new GroupPersistanceResolver(groupRepository);
    }

}

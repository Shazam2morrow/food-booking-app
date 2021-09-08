package food.booking.app.business.adapter.out.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Group persistence adapter configuration
 *
 * @author shazam2morrow
 */
@Configuration
class GroupPersistenceAdapterConfig {

    @Bean
    GroupPersistenceAdapter groupPersistenceAdapter(GroupRepository groupRepository,
                                                    GroupPersistenceMapper groupPersistenceMapper,
                                                    GroupPersistenceResolver groupPersistenceResolver) {
        return new GroupPersistenceAdapter(groupRepository, groupPersistenceMapper, groupPersistenceResolver);
    }

    @Bean
    GroupPersistenceMapper groupPersistenceMapper(MenuPersistenceResolver menuPersistenceResolver, GroupPersistenceResolver groupPersistenceResolver) {
        return new GroupPersistenceMapper(menuPersistenceResolver, groupPersistenceResolver);
    }

    @Bean
    GroupPersistenceResolver groupPersistenceResolver(GroupRepository groupRepository) {
        return new GroupPersistenceResolver(groupRepository);
    }

}

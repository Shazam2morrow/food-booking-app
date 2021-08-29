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
    GroupPersistenceAdapter grouppersistenceAdapter(GroupRepository groupRepository,
                                                    GroupPersistenceMapper groupPersistenceMapper,
                                                    GroupPersistenceResolver groupPersistenceResolver) {
        return new GroupPersistenceAdapter(groupRepository, groupPersistenceMapper, groupPersistenceResolver);
    }

    @Bean
    GroupPersistenceMapper grouppersistenceMapper(MenuPersistenceResolver menuPersistenceResolver) {
        return new GroupPersistenceMapper(menuPersistenceResolver);
    }

    @Bean
    GroupPersistenceResolver grouppersistenceResolver(GroupRepository groupRepository) {
        return new GroupPersistenceResolver(groupRepository);
    }

}

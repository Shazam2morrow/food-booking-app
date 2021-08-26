package food.booking.app.business.app;

import food.booking.app.business.app.port.out.group.*;
import food.booking.app.storage.app.FileUriResolver;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Group service configuration
 *
 * @author shazam2morrow
 */
@Configuration
class GroupServiceConfig {

    @Bean
    GroupService groupService(CreateGroupPort createGroupPort,
                              GroupServiceMapper groupServiceMapper,
                              LoadGroupDetailsPort loadGroupDetailsPort,
                              LoadMenuGroupListPort loadMenuGroupListPort,
                              UpdateGroupDetailsPort updateGroupDetailsPort) {
        return new GroupService(
                createGroupPort,
                groupServiceMapper,
                loadGroupDetailsPort,
                loadMenuGroupListPort,
                updateGroupDetailsPort);
    }

    @Bean
    GroupServiceMapper groupServiceMapper(FileUriResolver fileUriResolver,
                                          CheckGroupSlugPort checkGroupSlugPort,
                                          RandomStringGenerator randomStringGenerator) {
        return new GroupServiceMapper(fileUriResolver, checkGroupSlugPort, randomStringGenerator);
    }

}

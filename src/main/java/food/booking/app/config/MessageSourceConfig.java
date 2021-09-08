package food.booking.app.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Message source configuration
 *
 * @author shazam2morrow
 */
@Configuration
class MessageSourceConfig {

    @Bean
    @Primary
    MessageSource messageSource() {
        var source = new ResourceBundleMessageSource();
        source.setDefaultEncoding("UTF-8");
        source.setBasename("messages/errors");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

}

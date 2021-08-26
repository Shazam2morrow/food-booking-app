package food.booking.app.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

/**
 * Properties coniguration
 *
 * @author shazam2morrow
 */
@Configuration
@ConfigurationPropertiesScan(basePackages = {"food.booking.app"})
class PropertiesConfig {
}

package food.booking.app.config;

import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Geometry factory configuration
 *
 * @author shazam2morrow
 */
@Configuration
class GeometryFactoryConfig {

    /**
     * Geometry factory
     *
     * @return geometry factory
     */
    @Bean
    GeometryFactory geometryFactory() {
        return new GeometryFactory();
    }

}

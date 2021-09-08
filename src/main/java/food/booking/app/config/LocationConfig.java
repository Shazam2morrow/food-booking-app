package food.booking.app.config;

import food.booking.app.shared.domain.mapper.LocationMapper;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Location configuration
 *
 * @author shazam2morrow
 */
@Configuration
class LocationConfig {

    @Bean
    LocationMapper locationMapper(GeometryFactory geometryFactory) {
        return new LocationMapper(geometryFactory);
    }

}

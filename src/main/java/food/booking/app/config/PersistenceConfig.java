package food.booking.app.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Persistance configuration
 *
 * @author shazam2morrow
 */
@EnableJpaAuditing
@EnableTransactionManagement
@EntityScan(basePackages = {
        "food.booking.app.storage.adapter.out.persistance",
        "food.booking.app.business.adapter.out.persistance"
})
@EnableJpaRepositories(basePackages = {
        "food.booking.app.storage.adapter.out.persistance",
        "food.booking.app.business.adapter.out.persistance"
})
class PersistenceConfig {
}


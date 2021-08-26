package food.booking.app.config;

import org.apache.commons.rng.simple.RandomSource;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Random generator configuration
 *
 * @author shazam2morrow
 */
@Configuration
class RandomGeneratorConfig {

    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._";

    /**
     * Create random string generator
     *
     * @return random string generator
     */
    @Bean
    RandomStringGenerator randomStringGenerator() {
        return new RandomStringGenerator.Builder()
                .usingRandom(RandomSource.create(RandomSource.MT)::nextInt)
                .selectFrom(ALPHABET.toCharArray())
                .build();
    }

}

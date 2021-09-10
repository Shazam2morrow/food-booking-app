package food.booking.app.shared;

import food.booking.app.shared.size.SlugSize;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;

/**
 * Slug generator
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
public abstract class SlugGenerator {

    protected final CanCheckSlug canCheckSlug;

    protected final RandomStringGenerator randomStringGenerator;

    private final static String CANT_START_WITH = "-._";

    /**
     * Generate slug
     * <p>
     * This method generates unique slug
     * that does not contain any wrong characters in the begining
     * and is unique for the given object
     *
     * @return generated slug
     */
    protected String generateSlug() {
        String slug;
        do {
            slug = randomStringGenerator.generate(SlugSize.MAX);
        } while (StringUtils.startsWithAny(slug, CANT_START_WITH) || canCheckSlug.check(slug));
        return slug;
    }

}

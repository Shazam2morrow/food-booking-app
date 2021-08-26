package food.booking.app.business.app.port.in.category;

/**
 * Delete category use case
 *
 * @author shazam2morrow
 */
public interface DeleteCategoryUseCase {

    /**
     * Delete category
     *
     * @param categorySlug category slug
     */
    void deleteBySlug(String categorySlug);

}

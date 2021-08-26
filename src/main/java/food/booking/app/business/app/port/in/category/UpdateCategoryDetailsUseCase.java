package food.booking.app.business.app.port.in.category;

/**
 * Update category details use case
 *
 * @author shazam2morrow
 */
public interface UpdateCategoryDetailsUseCase {

    /**
     * Update category details command
     *
     * @param command update category details command
     */
    void update(UpdateCategoryDetailsCommand command);

}

package food.booking.app.business.app.port.in.item;

/**
 * Delete item use case
 *
 * @author shazam2morrow
 */
public interface DeleteItemUseCase {

    /**
     * Delete item
     *
     * @param itemSlug item slug
     */
    void deleteBySlug(String itemSlug);

}

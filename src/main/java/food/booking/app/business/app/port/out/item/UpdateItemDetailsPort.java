package food.booking.app.business.app.port.out.item;

/**
 * Update item details port
 *
 * @author shazam2morrow
 */
public interface UpdateItemDetailsPort {

    /**
     * Update item details
     *
     * @param details update item details
     */
    void update(UpdateItemDetails details);

}

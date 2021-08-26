package food.booking.app.business.app.port.in.group;

/**
 * Delete group use case
 *
 * @author shazam2morrow
 */
public interface DeleteGroupUseCase {

    /**
     * Delete group use case
     *
     * @param groupSlug group slug
     */
    void deleteBySlug(String groupSlug);

}

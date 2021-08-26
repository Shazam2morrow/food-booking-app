package food.booking.app.business.app.port.in.group;

import food.booking.app.business.domain.Group;

import java.util.List;

/**
 * Load menu group list use case
 *
 * @author shazam2morrow
 */
public interface LoadMenuGroupListUseCase {

    /**
     * Load list of menu groups
     *
     * @param menuSlug menu slug
     * @return list of menu groups
     */
    List<Group> loadListByMenuSlug(String menuSlug);

}

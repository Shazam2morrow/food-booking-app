package food.booking.app.business.app.port.out.group;

import food.booking.app.business.domain.Menu;

import java.net.URI;

/**
 * Create group
 *
 * @author shazam2morrow
 */
public record CreateGroup(Menu menu, String slug, String title, Short sortOrder, URI iconURl) {
}

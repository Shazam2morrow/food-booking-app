package food.booking.app.business.app.port.out.menu;

import food.booking.app.business.domain.Restaurant;

/**
 * Create menu
 *
 * @author shazam2morrow
 */
public record CreateMenu(String slug, String title, Short sortOrder, Restaurant restaurant) {
}

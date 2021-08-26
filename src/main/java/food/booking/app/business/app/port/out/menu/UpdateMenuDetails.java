package food.booking.app.business.app.port.out.menu;

/**
 * Update menu details
 *
 * @author shazam2morrow
 */
public record UpdateMenuDetails(String slug, String title, Short sortOrder, Boolean active) {
}

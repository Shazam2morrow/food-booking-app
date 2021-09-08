package food.booking.app.business.adapter.in.web.group;

import food.booking.app.business.domain.Menu;
import food.booking.app.shared.validation.FileUrlOrNull;
import food.booking.app.shared.validation.SortOrder;
import food.booking.app.shared.validation.Title;

import javax.validation.constraints.NotNull;
import java.net.URI;

/**
 * Group
 *
 * @author shazam2morrow
 */
record Group(@Title String title,
             @FileUrlOrNull URI iconUrl,
             @SortOrder Short sortOrder,
             @NotNull(message = "group.menu.notnull") Menu menu) {
}

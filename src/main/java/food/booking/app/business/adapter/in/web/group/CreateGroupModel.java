package food.booking.app.business.adapter.in.web.group;

import food.booking.app.business.domain.Menu;
import food.booking.app.shared.validation.Title;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.net.URI;

/**
 * Create group model
 *
 * @author shazam2morrow
 */
record CreateGroupModel(@Title String title,
                        @Nullable URI iconUrl,
                        @Nullable Short sortOrder,
                        @NotNull(message = "menu can not be null") Menu menu) {
}

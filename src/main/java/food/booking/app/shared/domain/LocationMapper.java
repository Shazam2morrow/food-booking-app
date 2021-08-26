package food.booking.app.shared.domain;

import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Location mapper
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
public class LocationMapper {

    private final GeometryFactory geometryFactory;

    /**
     * Map to location
     *
     * @param point point
     * @return location or null if point is null
     */
    @Nullable
    public Location mapToLocation(Point point) {
        return Objects.isNull(point) ? null : new Location(point.getX(), point.getY());
    }

    /**
     * Map to point
     *
     * @param location location
     * @return point or null if location is null
     */
    @Nullable
    public Point mapToPoint(Location location) {
        return Objects.isNull(location)
                ? null : geometryFactory.createPoint(new Coordinate(location.getLongitude(), location.getLatitude()));
    }

}

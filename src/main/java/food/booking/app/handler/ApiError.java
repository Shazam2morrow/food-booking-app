package food.booking.app.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;

/**
 * API error
 *
 * @author shazam2morrow
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiError {

    private final String path;

    private final Integer code;

    private final String method;

    private final String message;

    private final Instant timestamp;

    private final HttpStatus status;

    @Nullable
    private final String details;

    @Nullable
    private final List<ApiSubError> subErrors;

    ApiError(String message,
             String path,
             HttpStatus status,
             Integer code,
             String method,
             @Nullable String details,
             @Nullable List<ApiSubError> subErrors) {
        this.code = code;
        this.path = path;
        this.status = status;
        this.method = method;
        this.message = message;
        this.details = details;
        this.subErrors = subErrors;
        this.timestamp = Instant.now();
    }

    ApiError(String message, String path, HttpStatus status, Integer code, String method) {
        this(message, path, status, code, method, null, null);
    }

}

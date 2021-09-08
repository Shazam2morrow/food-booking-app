package food.booking.app.handler;

import lombok.Getter;

/**
 * API validation error
 *
 * @author shazam2morrow
 */
@Getter
class ApiValidationError extends ApiSubError {

    private final String field;

    private final String message;

    private final String objectName;

    private final Object rejectedValue;

    public final static String MESSAGE = "validation.fail";

    public final static String DETAILS = "validation.fail.details";

    ApiValidationError(String field, String message, String objectName, Object rejectedValue) {
        this.field = field;
        this.message = message;
        this.objectName = objectName;
        this.rejectedValue = rejectedValue;
    }

}

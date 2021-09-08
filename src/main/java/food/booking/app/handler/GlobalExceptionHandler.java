package food.booking.app.handler;

import food.booking.app.shared.exception.NoSuchElementException;
import food.booking.app.shared.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Global exception handler
 *
 * @author shazam2morrow
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {

    private final MessageSource messageSource;

    private final static String UNKNOW_ERROR = "unknown.error";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ApiError> handleUncaughtException(
            Exception ex,
            Locale locale,
            HttpServletRequest request) {
        log.error("Unknow error occured", ex);
        String path = request.getRequestURL().toString();
        String message = messageSource.getMessage(UNKNOW_ERROR, new Object[]{}, locale);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(new ApiError(message, path, status, status.value(), request.getMethod()), status);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ApiError> handleNoSuchElementException(
            NoSuchElementException ex,
            Locale locale,
            HttpServletRequest request) {
        String path = request.getRequestURL().toString();
        String message = messageSource.getMessage(ex.getMessageCode(), new Object[]{ex.getId()}, locale);
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ApiError(message, path, status, status.value(), request.getMethod()), status);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ApiError> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex,
            Locale locale,
            HttpServletRequest request) {
        log.error("Could not read input message", ex);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ApiError> handleServiceException(
            ServiceException ex,
            Locale locale,
            HttpServletRequest request) {
        log.error("Service error happened", ex);
        String path = request.getRequestURL().toString();
        String message = messageSource.getMessage(ex.getMessageCode(), new Object[]{}, locale);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(new ApiError(message, path, status, status.value(), request.getMethod()), status);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ApiError> handleConstraintViolationException(
            ConstraintViolationException ex,
            Locale locale,
            HttpServletRequest request) {
        String message = messageSource.getMessage(ApiValidationError.MESSAGE, new Object[]{}, locale);
        String details = messageSource.getMessage(ApiValidationError.DETAILS, new Object[]{}, locale);
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        var error = new ApiError(
                message,
                request.getRequestURL().toString(),
                status,
                status.value(),
                request.getMethod(),
                details,
                buildErrors(ex));
        return new ResponseEntity<>(error, status);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiError> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            Locale locale,
            HttpServletRequest request) {
        String message = messageSource.getMessage(ApiValidationError.MESSAGE, new Object[]{}, locale);
        String details = messageSource.getMessage(ApiValidationError.DETAILS, new Object[]{}, locale);
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        var error = new ApiError(
                message,
                request.getRequestURL().toString(),
                status,
                status.value(),
                request.getMethod(),
                details,
                buildErrors(ex));
        return new ResponseEntity<>(error, status);
    }

    private List<ApiSubError> buildErrors(ConstraintViolationException ex) {
        return ex.getConstraintViolations()
                .stream()
                .map(violation -> new ApiValidationError(
                        violation.getPropertyPath().toString(),
                        violation.getMessage(),
                        violation.getRootBean().toString(),
                        violation.getInvalidValue()))
                .sorted(Comparator.comparing(ApiValidationError::getField))
                .collect(Collectors.toList());
    }

    private List<ApiSubError> buildErrors(MethodArgumentNotValidException ex) {
        return ex.getFieldErrors()
                .stream()
                .map(fieldError -> new ApiValidationError(
                        fieldError.getField(),
                        fieldError.getDefaultMessage(),
                        fieldError.getObjectName(),
                        fieldError.getRejectedValue()))
                .sorted(Comparator.comparing(ApiValidationError::getField))
                .collect(Collectors.toList());
    }

}

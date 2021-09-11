package food.booking.app.handler;

import food.booking.app.shared.exception.NoSuchElementException;
import food.booking.app.shared.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

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

    private final static String UNKNOW_ERROR_CODE = "server.fail";

    private final static String UNKNOW_ERROR_DETAILS_CODE = "server.fail.details";

    @Value("${spring.servlet.multipart.max-file-size:-1}")
    private String maxUploadSize;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ApiError> handleUncaughtException(Exception ex, HttpServletRequest request) {
        log.error("Unexpected server error occured!", ex);
        Locale locale = request.getLocale();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        var apiError = new ApiError(
                messageSource.getMessage(UNKNOW_ERROR_CODE, new Object[]{}, locale),
                request.getRequestURL().toString(),
                status,
                status.value(),
                request.getMethod(),
                messageSource.getMessage(UNKNOW_ERROR_DETAILS_CODE, new Object[]{}, locale));
        return new ResponseEntity<>(apiError, status);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ApiError> handleNoSuchElementException(NoSuchElementException ex,
                                                                    HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        var apiError = new ApiError(
                messageSource.getMessage(ex.getMessageCode(), new Object[]{ex.getId()}, request.getLocale()),
                request.getRequestURL().toString(),
                status,
                status.value(),
                request.getMethod());
        return new ResponseEntity<>(apiError, status);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ApiError> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("Could not read input message", ex);
        return ResponseEntity.badRequest().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestPartException.class)
    protected ResponseEntity<ApiError> handleMissingServletRequestPartException(
            MissingServletRequestPartException ex,
            HttpServletRequest request) {
        String requestPartName = ex.getRequestPartName();
        Locale locale = request.getLocale();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        var apiError = new ApiError(
                messageSource.getMessage("request.part.notpresent", new Object[]{requestPartName}, locale),
                request.getRequestURL().toString(),
                status,
                status.value(),
                request.getMethod());
        return new ResponseEntity<>(apiError, status);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity<ApiError> handleMaxUploadSizeExceededException(
            MaxUploadSizeExceededException ex,
            HttpServletRequest request) {
        Locale locale = request.getLocale();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        var apiError = new ApiError(
                messageSource.getMessage("file.size.exceeded", new Object[]{maxUploadSize}, locale),
                request.getRequestURL().toString(),
                status,
                status.value(),
                request.getMethod(),
                messageSource.getMessage("file.size.exceeded.details", new Object[]{}, locale));
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ApiError> handleServiceException(ServiceException ex, HttpServletRequest request) {
        log.error("Service error occured!", ex);
        Locale locale = request.getLocale();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        var apiError = new ApiError(
                messageSource.getMessage(ex.getMessageCode(), new Object[]{}, locale),
                request.getRequestURL().toString(),
                status,
                status.value(),
                request.getMethod(),
                messageSource.getMessage(UNKNOW_ERROR_DETAILS_CODE, new Object[]{}, locale));
        return new ResponseEntity<>(apiError, status);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException ex,
                                                                          HttpServletRequest request) {
        Locale locale = request.getLocale();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        var error = new ApiError(
                messageSource.getMessage(ApiValidationError.MESSAGE, new Object[]{}, locale),
                request.getRequestURL().toString(),
                status,
                status.value(),
                request.getMethod(),
                messageSource.getMessage(ApiValidationError.DETAILS, new Object[]{}, locale),
                buildErrors(ex, locale));
        return new ResponseEntity<>(error, status);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                             HttpServletRequest request) {
        Locale locale = request.getLocale();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        var error = new ApiError(
                messageSource.getMessage(ApiValidationError.MESSAGE, new Object[]{}, locale),
                request.getRequestURL().toString(),
                status,
                status.value(),
                request.getMethod(),
                messageSource.getMessage(ApiValidationError.DETAILS, new Object[]{}, locale),
                buildErrors(ex));
        return new ResponseEntity<>(error, status);
    }

    private List<ApiSubError> buildErrors(ConstraintViolationException ex, Locale locale) {
        return ex.getConstraintViolations()
                .stream()
                .map(violation -> new ApiValidationError(
                        violation.getPropertyPath().toString(),
                        messageSource.getMessage(violation.getMessage(), new Object[]{}, locale),
                        violation.getRootBeanClass().getSimpleName().toLowerCase(),
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

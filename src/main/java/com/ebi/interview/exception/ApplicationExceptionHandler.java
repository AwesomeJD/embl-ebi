package com.ebi.interview.exception;

import com.ebi.interview.constants.ErrorConstants;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/** The type Application exception handler. */
@ControllerAdvice
public class ApplicationExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    /**
     * Handle service exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler({ApplicationException.class})
    public final ResponseEntity<Object> handleServiceException(ApplicationException ex) {
        LOGGER.error(
                "Application exception occurred.",
                (Throwable) ex.getOriginalException().orElse(ex));
        Map<String, Object> responseError =
                this.getResponseBodyMap(ex.getErrorCode(), ex.getErrorMessage(), "Business Error");
        LOGGER.trace("Error response :{}", responseError);
        return new ResponseEntity(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle constraint violation response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public final ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        LOGGER.error("ConstraintViolationException exception occurred due to bad user input.", ex);
        List<String> errors =
                ex.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList());
        Map<String, Object> responseError =
                this.getResponseBodyMap(
                        ErrorConstants.BAD_USER_INPUT_ERROR_CODE,
                        errors.toString(),
                        "BAD USER INPUT");
        LOGGER.trace("Error response :{}", responseError);
        return new ResponseEntity(responseError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle method argument not valid exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        LOGGER.error(
                "MethodArgumentNotValidException exception occurred due to bad user input.", ex);
        List<String> errors =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());
        Map<String, Object> responseError =
                this.getResponseBodyMap(
                        ErrorConstants.BAD_USER_INPUT_ERROR_CODE,
                        errors.toString(),
                        "BAD USER INPUT");
        LOGGER.trace("Error response :{}", responseError);
        return new ResponseEntity(responseError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle method argument type mismatch exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public final ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        LOGGER.error(
                "MethodArgumentTypeMismatchException exception occurred due to bad user input.",
                ex);
        String name = ex.getName();
        String type = Objects.requireNonNull(ex.getRequiredType()).getSimpleName();
        Object value = ex.getValue();
        String message =
                String.format("'%s' should be a valid '%s' and '%s' isn't", name, type, value);

        Map<String, Object> responseError =
                this.getResponseBodyMap(
                        ErrorConstants.BAD_USER_INPUT_ERROR_CODE, message, "BAD USER INPUT");
        LOGGER.trace("Error response :{}", responseError);
        return new ResponseEntity(responseError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler({Exception.class})
    public final ResponseEntity<Object> handleException(Exception ex) {
        LOGGER.error("General exception occurred. {}", ex);
        Map<String, Object> responseError =
                this.getResponseBodyMap(
                        ErrorConstants.GENERIC_ERROR_CODE,
                        ErrorConstants.GENERIC_ERROR_MESSAGE,
                        "Technical Error");
        LOGGER.trace("Error response :{}", responseError);
        return new ResponseEntity(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> getResponseBodyMap(
            String errorCode, String errorMessage, String errorType) {
        return ImmutableMap.of(
                "errors",
                ImmutableList.of(
                        ImmutableMap.of(
                                "code", errorCode, "message", errorMessage, "type", errorType)));
    }
}

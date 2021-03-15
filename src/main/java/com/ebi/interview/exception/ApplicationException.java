package com.ebi.interview.exception;

import java.util.Optional;

/**
 * The Exception for the whole application. This helps in the uniformity of the exception and error
 * handling.
 */
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String errorCode;
    private final String errorMessage;
    private final Throwable originalException;

    /**
     * Instantiates a new Application exception.
     *
     * @param errorCode the error code
     * @param errorMessage the error message
     */
    public ApplicationException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.originalException = null;
    }

    /**
     * Instantiates a new Application exception.
     *
     * @param errorCode the error code
     * @param errorMessage the error message
     * @param originalException the original exception
     */
    public ApplicationException(
            String errorCode, String errorMessage, Throwable originalException) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.originalException = originalException;
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Gets original exception.
     *
     * @return the original exception
     */
    public Optional<Throwable> getOriginalException() {
        return Optional.ofNullable(this.originalException);
    }
}

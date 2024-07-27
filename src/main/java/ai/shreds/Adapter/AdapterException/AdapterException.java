package ai.shreds.adapter;

import ai.shreds.shared.SharedErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application.
 */
@RestControllerAdvice
public class AdapterException {

    /**
     * Handles generic exceptions and converts them to a standard error response.
     *
     * @param e the exception to handle
     * @return a standardized error response
     */
    @ExceptionHandler(Exception.class)
    public SharedErrorResponse handleException(Exception e) {
        SharedErrorResponse errorResponse = new SharedErrorResponse();
        errorResponse.setError(e.getMessage());
        return errorResponse;
    }
}
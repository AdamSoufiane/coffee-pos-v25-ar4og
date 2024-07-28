package ai.shreds.Adapter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import shared.SharedErrorResponse;

@ControllerAdvice
public class AdapterExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<SharedErrorResponse> handleNotFoundException(NotFoundException e) {
        SharedErrorResponse errorResponse = new SharedErrorResponse();
        errorResponse.setError(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SharedErrorResponse> handleBadRequestException(BadRequestException e) {
        SharedErrorResponse errorResponse = new SharedErrorResponse();
        errorResponse.setError(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<SharedErrorResponse> handleInternalServerError(Exception e) {
        SharedErrorResponse errorResponse = new SharedErrorResponse();
        errorResponse.setError("Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
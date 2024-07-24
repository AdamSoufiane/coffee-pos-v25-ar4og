package ai.shreds.adapter; 
  
 import ai.shreds.shared.SharedErrorResponse; 
 import ai.shreds.domain.exceptions.InvalidInputException; 
 import ai.shreds.domain.exceptions.CategoryNotFoundException; 
 import ai.shreds.domain.exceptions.ServerErrorException; 
 import org.springframework.dao.DataAccessException; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ControllerAdvice; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @ControllerAdvice 
 public class AdapterExceptionHandler { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterExceptionHandler.class); 
  
     /** 
      * Handles InvalidInputException and returns a bad request response. 
      * 
      * @param e the InvalidInputException 
      * @return ResponseEntity with SharedErrorResponse and BAD_REQUEST status 
      */ 
     @ExceptionHandler(InvalidInputException.class) 
     public ResponseEntity<SharedErrorResponse> handleInvalidInputException(InvalidInputException e) { 
         logger.error("Invalid input error: {}", e.getMessage()); 
         SharedErrorResponse errorResponse = new SharedErrorResponse(e.getMessage()); 
         return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); 
     } 
  
     /** 
      * Handles CategoryNotFoundException and returns a not found response. 
      * 
      * @param e the CategoryNotFoundException 
      * @return ResponseEntity with SharedErrorResponse and NOT_FOUND status 
      */ 
     @ExceptionHandler(CategoryNotFoundException.class) 
     public ResponseEntity<SharedErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException e) { 
         logger.error("Category not found: {}", e.getMessage()); 
         SharedErrorResponse errorResponse = new SharedErrorResponse(e.getMessage()); 
         return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); 
     } 
  
     /** 
      * Handles ServerErrorException and returns an internal server error response. 
      * 
      * @param e the ServerErrorException 
      * @return ResponseEntity with SharedErrorResponse and INTERNAL_SERVER_ERROR status 
      */ 
     @ExceptionHandler(ServerErrorException.class) 
     public ResponseEntity<SharedErrorResponse> handleServerErrorException(ServerErrorException e) { 
         logger.error("Server error: {}", e.getMessage()); 
         SharedErrorResponse errorResponse = new SharedErrorResponse(e.getMessage()); 
         return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
  
     /** 
      * Handles DataAccessException and returns an internal server error response. 
      * 
      * @param e the DataAccessException 
      * @return ResponseEntity with SharedErrorResponse and INTERNAL_SERVER_ERROR status 
      */ 
     @ExceptionHandler(DataAccessException.class) 
     public ResponseEntity<SharedErrorResponse> handleDataAccessException(DataAccessException e) { 
         logger.error("Database error: {}", e.getMessage()); 
         SharedErrorResponse errorResponse = new SharedErrorResponse("A database error occurred."); 
         return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
  
     /** 
      * Handles unexpected exceptions and returns an internal server error response. 
      * 
      * @param e the Exception 
      * @return ResponseEntity with SharedErrorResponse and INTERNAL_SERVER_ERROR status 
      */ 
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<SharedErrorResponse> handleUnexpectedException(Exception e) { 
         logger.error("Unexpected error: {}", e.getMessage()); 
         SharedErrorResponse errorResponse = new SharedErrorResponse("An unexpected error occurred."); 
         return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
 } 
 
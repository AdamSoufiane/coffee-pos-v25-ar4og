package ai.shreds.adapter; 
  
 import ai.shreds.shared.SharedCreateOrderRequestParams; 
 import ai.shreds.shared.SharedOrderResponseDTO; 
 import ai.shreds.application.ApplicationCreateOrderInputPort; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.PostMapping; 
 import org.springframework.web.bind.annotation.RequestBody; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RestController; 
  
 import javax.validation.Valid; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import javax.validation.constraints.NotNull; 
  
 @RestController 
 @RequestMapping("/api/orders") 
 public class AdapterOrderController { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterOrderController.class); 
     private final ApplicationCreateOrderInputPort createOrderService; 
  
     public AdapterOrderController(ApplicationCreateOrderInputPort createOrderService) { 
         this.createOrderService = createOrderService; 
     } 
  
     @PostMapping 
     public ResponseEntity<SharedOrderResponseDTO> createOrder(@Valid @RequestBody SharedCreateOrderRequestParams request) { 
         if (request.getUserId() == null || request.getItems() == null || request.getItems().isEmpty()) { 
             return new ResponseEntity<>("Invalid request: userId and items must not be null", HttpStatus.BAD_REQUEST); 
         } 
         try { 
             logger.info("Received order creation request: {}", request); 
             SharedOrderResponseDTO response = createOrderService.createOrder(request); 
             logger.info("Order created successfully: {}", response); 
             return new ResponseEntity<>(response, HttpStatus.CREATED); 
         } catch (ItemNotFoundException e) { 
             logger.error("Item not found: {}", e.getMessage()); 
             return new ResponseEntity<>("Item not found: " + e.getMessage(), HttpStatus.BAD_REQUEST); 
         } catch (Exception e) { 
             logger.error("Internal server error: {}", e.getMessage()); 
             return new ResponseEntity<>("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
         } 
     } 
 } 
 
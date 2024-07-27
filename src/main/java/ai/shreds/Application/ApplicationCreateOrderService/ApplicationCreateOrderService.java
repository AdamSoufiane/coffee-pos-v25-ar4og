package ai.shreds.application; 
  
 import ai.shreds.shared.SharedCreateOrderRequestParams; 
 import ai.shreds.shared.SharedOrderResponseDTO; 
 import ai.shreds.domain.DomainOrderService; 
 import ai.shreds.domain.DomainUserService; 
 import ai.shreds.domain.DomainInventoryService; 
 import ai.shreds.domain.DomainKafkaProducerService; 
 import ai.shreds.domain.DomainOrderMapper; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.stereotype.Service; 
 import javax.validation.Valid; 
 import javax.validation.constraints.NotNull; 
  
 @Service 
 @RequiredArgsConstructor 
 @Slf4j 
 public class ApplicationCreateOrderService implements ApplicationCreateOrderInputPort { 
     private final DomainOrderService domainOrderService; 
     private final DomainUserService domainUserService; 
     private final DomainInventoryService domainInventoryService; 
     private final DomainKafkaProducerService domainKafkaProducerService; 
     private final DomainOrderMapper orderMapper; 
  
     @Override 
     public SharedOrderResponseDTO createOrder(@Valid @NotNull SharedCreateOrderRequestParams request) { 
         try { 
             log.info("Starting order creation process for user: {}", request.getUserId()); 
  
             // Validate the user information 
             boolean isUserValid = domainUserService.validateUser(request.getUserId()); 
             if (!isUserValid) { 
                 log.error("User validation failed for user ID: {}", request.getUserId()); 
                 throw new IllegalArgumentException("Invalid user ID"); 
             } 
  
             // Check the availability of each item in the order 
             request.getItems().forEach(item -> { 
                 boolean isItemAvailable = domainInventoryService.checkItemAvailability(item.getItemId(), item.getQuantity()); 
                 if (!isItemAvailable) { 
                     log.error("Item availability check failed for item ID: {}", item.getItemId()); 
                     throw new IllegalArgumentException("Item not available: " + item.getItemId()); 
                 } 
             }); 
  
             // Map the request data to a DomainOrderEntity 
             DomainOrderEntity orderEntity = orderMapper.toEntity(request); 
  
             // Save the order entity to the database 
             domainOrderService.save(orderEntity); 
  
             // Publish an OrderPlaced event to Kafka 
             domainKafkaProducerService.sendOrderPlacedEvent(orderEntity); 
  
             // Map the saved order entity to a SharedOrderResponseDTO 
             SharedOrderResponseDTO responseDTO = orderMapper.toDTO(orderEntity); 
             log.info("Order created successfully with order ID: {}", responseDTO.getOrderId()); 
             return responseDTO; 
         } catch (IllegalArgumentException e) { 
             log.error("Order creation failed due to invalid input: ", e); 
             throw e; 
         } catch (Exception e) { 
             log.error("Unexpected error during order creation: ", e); 
             throw new RuntimeException("Failed to create order"); 
         } 
     } 
 }
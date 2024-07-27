package ai.shreds.domain; 
  
 import java.util.UUID; 
 import lombok.RequiredArgsConstructor; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.stereotype.Service; 
 import ai.shreds.infrastructure.InventoryServiceClient; 
  
 @Service 
 @RequiredArgsConstructor 
 public class DomainInventoryService implements DomainInventoryCheckPort { 
     private static final Logger logger = LoggerFactory.getLogger(DomainInventoryService.class); 
     private final InventoryServiceClient inventoryServiceClient; 
  
     @Override 
     public boolean checkItemAvailability(UUID itemId, int quantity) { 
         try { 
             return inventoryServiceClient.checkItemAvailability(itemId, quantity); 
         } catch (InventoryServiceException e) { 
             logger.error("Error checking item availability for itemId: {} and quantity: {}", itemId, quantity, e); 
             return false; 
         } 
     } 
 } 
  
 // implementation-note: Use @Slf4j annotation from Lombok for logging.
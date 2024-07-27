package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Getter; 
 import lombok.NoArgsConstructor; 
 import lombok.ToString; 
  
 import java.util.List; 
 import java.util.UUID; 
  
 @Getter 
 @AllArgsConstructor 
 @NoArgsConstructor 
 @ToString 
 public class SharedCreateOrderRequestParams { 
     private UUID userId; 
     private List<SharedOrderItemRequestParams> items; 
  
     // Validation to ensure userId and items are not null or empty 
     public void validate() { 
         if (userId == null) { 
             throw new IllegalArgumentException("UserId cannot be null"); 
         } 
         if (items == null || items.isEmpty()) { 
             throw new IllegalArgumentException("Items cannot be null or empty"); 
         } 
     } 
 } 
 // Note: Consider using Lombok's @Builder and @Value annotations for immutability and builder pattern. 
 
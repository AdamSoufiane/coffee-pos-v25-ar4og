package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedUpdateStockRequestDTO { 
     private String itemId; 
     private Integer newQuantity; 
 } 
  
 // Implementation Note: Ensure Lombok annotations are used for generating getters, setters, constructors, and other common methods. 
 
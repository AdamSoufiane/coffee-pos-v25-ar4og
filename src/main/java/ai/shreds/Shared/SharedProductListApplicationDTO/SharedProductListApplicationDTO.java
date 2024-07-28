package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Data Transfer Object (DTO) for transferring product list data. 
  * This DTO encapsulates the data for a list of products including id, name, description, categoryId, price, and stock. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedProductListApplicationDTO { 
     private String id; 
     private String name; 
     private String description; 
     private String categoryId; 
     private Double price; 
     private Integer stock; 
 }
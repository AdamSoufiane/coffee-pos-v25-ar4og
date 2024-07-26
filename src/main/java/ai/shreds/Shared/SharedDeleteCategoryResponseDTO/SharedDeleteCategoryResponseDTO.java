package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Data Transfer Object (DTO) for the response of deleting a category. 
  * Contains a message indicating the result of the delete operation. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedDeleteCategoryResponseDTO { 
     private String message; 
 }
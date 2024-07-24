package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Response DTO for updating a category. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedUpdateCategoryResponse { 
     /** 
      * Unique identifier for the category. 
      */ 
     private String id; 
  
     /** 
      * Name of the category. 
      */ 
     private String name; 
  
     /** 
      * Description of the category. 
      */ 
     private String description; 
 } 
  
 // Note: This class adheres to the DTO (Data Transfer Object) Pattern as per the shred specifications. 
 
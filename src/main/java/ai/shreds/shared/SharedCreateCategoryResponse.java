package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Response DTO for creating a category. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedCreateCategoryResponse { 
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
package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import javax.validation.constraints.NotBlank; 
 import javax.validation.constraints.Size; 
  
 /** 
  * Request object for updating a category. 
  */ 
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class SharedUpdateCategoryRequest { 
     /** 
      * Unique identifier for the category. 
      */ 
     @NotBlank(message = "ID cannot be blank") 
     private String id; 
     /** 
      * Name of the category. 
      */ 
     @NotBlank(message = "Name cannot be blank") 
     private String name; 
     /** 
      * Description of the category. 
      */ 
     @Size(max = 500, message = "Description cannot exceed 500 characters") 
     private String description; 
 }
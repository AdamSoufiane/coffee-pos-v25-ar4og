package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedCreateCategoryResponseDTO; 
 import ai.shreds.shared.SharedUpdateCategoryResponseDTO; 
 import ai.shreds.shared.SharedDeleteCategoryResponseDTO; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import org.bson.types.ObjectId; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class DomainCategoryEntity { 
     private ObjectId id; 
     private String name; 
     private String description; 
  
     public SharedCreateCategoryResponseDTO toCreateCategoryResponseDTO() { 
         return new SharedCreateCategoryResponseDTO(id.toHexString(), name, description); 
     } 
  
     public SharedUpdateCategoryResponseDTO toUpdateCategoryResponseDTO() { 
         return new SharedUpdateCategoryResponseDTO(id.toHexString(), name, description); 
     } 
  
     public SharedDeleteCategoryResponseDTO toDeleteCategoryResponseDTO() { 
         return new SharedDeleteCategoryResponseDTO("Category successfully deleted."); 
     } 
  
     public void validate() { 
         if (name == null || name.isEmpty()) { 
             throw new IllegalArgumentException("Category name must not be null or empty."); 
         } 
         if (description != null && description.length() > 500) { 
             throw new IllegalArgumentException("Category description must not exceed 500 characters."); 
         } 
     } 
 }
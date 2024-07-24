package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedCategoryDTO; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import org.springframework.data.annotation.Id; 
 import org.springframework.data.mongodb.core.mapping.Document; 
 import javax.validation.constraints.NotBlank; 
 import javax.validation.constraints.Size; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @Document(collection = "categories") 
 public class DomainCategoryEntity { 
     @Id 
     private String id; 
  
     @NotBlank(message = "Name is mandatory") 
     private String name; 
  
     @Size(max = 500, message = "Description must not exceed 500 characters") 
     private String description; 
  
     public SharedCategoryDTO toSharedCategoryDTO() { 
         SharedCategoryDTO dto = new SharedCategoryDTO(); 
         dto.setId(this.id); 
         dto.setName(this.name != null ? this.name : ""); 
         dto.setDescription(this.description != null ? this.description : ""); 
         return dto; 
     } 
 }
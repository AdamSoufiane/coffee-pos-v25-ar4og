package ai.shreds.shared; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import java.io.Serializable; 
  
 /** 
  * Data Transfer Object for Category. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedCategoryDTO implements Serializable { 
     private static final long serialVersionUID = 1L; 
  
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
  
     /** 
      * Maps this DTO to a DomainCategoryEntity. 
      * 
      * @return a DomainCategoryEntity object with the same data as this DTO. 
      */ 
     public DomainCategoryEntity internalMapper() { 
         validateFields(); 
         DomainCategoryEntity domainCategoryEntity = new DomainCategoryEntity(); 
         domainCategoryEntity.setId(this.id); 
         domainCategoryEntity.setName(this.name); 
         domainCategoryEntity.setDescription(this.description); 
         return domainCategoryEntity; 
     } 
  
     /** 
      * Validates the fields to ensure they are not null or empty. 
      */ 
     private void validateFields() { 
         if (id == null || id.isEmpty()) { 
             throw new IllegalArgumentException("Category ID cannot be null or empty"); 
         } 
         if (name == null || name.isEmpty()) { 
             throw new IllegalArgumentException("Category name cannot be null or empty"); 
         } 
         if (description == null || description.isEmpty()) { 
             throw new IllegalArgumentException("Category description cannot be null or empty"); 
         } 
     } 
 }
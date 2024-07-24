package ai.shreds.application; 
  
 import ai.shreds.domain.DomainCategoryService; 
 import ai.shreds.shared.SharedCategoryDTO; 
 import lombok.RequiredArgsConstructor; 
 import org.springframework.stereotype.Service; 
  
 /** 
  * Service implementation for creating categories. 
  */ 
 @Service 
 @RequiredArgsConstructor 
 public class ApplicationCreateCategoryService implements ApplicationCreateCategoryInputPort { 
     private final DomainCategoryService categoryService; 
  
     /** 
      * Creates a new category. 
      * 
      * @param name The name of the category. 
      * @param description The description of the category. 
      * @return The created category as a SharedCategoryDTO. 
      */ 
     @Override 
     public SharedCategoryDTO createCategory(String name, String description) { 
         // Ensure that validation logic is present in the DomainCategoryService's handleCategoryCreation method. 
         return categoryService.handleCategoryCreation(name, description); 
     } 
 }
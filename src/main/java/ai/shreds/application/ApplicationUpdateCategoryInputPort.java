package ai.shreds.application; 
  
 import ai.shreds.domain.DomainCategoryService; 
 import ai.shreds.shared.SharedCategoryDTO; 
 import ai.shreds.shared.SharedUpdateCategoryRequest; 
 import ai.shreds.shared.SharedUpdateCategoryResponse; 
 import lombok.RequiredArgsConstructor; 
 import org.springframework.stereotype.Service; 
  
 @Service 
 @RequiredArgsConstructor 
 public class ApplicationUpdateCategoryService implements ApplicationUpdateCategoryInputPort { 
  
     private final DomainCategoryService categoryService; 
  
     @Override 
     public SharedCategoryDTO updateCategory(String id, String name, String description) { 
         return categoryService.handleCategoryUpdate(id, name, description); 
     } 
 } 
 
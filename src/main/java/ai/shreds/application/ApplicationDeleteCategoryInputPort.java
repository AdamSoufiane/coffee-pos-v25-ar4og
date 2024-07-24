package ai.shreds.application; 
  
 import ai.shreds.domain.DomainCategoryService; 
 import ai.shreds.shared.SharedDeleteCategoryResponse; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import lombok.RequiredArgsConstructor; 
  
 public interface ApplicationDeleteCategoryInputPort { 
     SharedDeleteCategoryResponse deleteCategory(String id); 
 } 
  
 // Using Lombok for constructor injection 
 @RequiredArgsConstructor 
 public class ApplicationDeleteCategoryService implements ApplicationDeleteCategoryInputPort { 
     @Autowired 
     private final DomainCategoryService categoryService; 
  
     @Override 
     public SharedDeleteCategoryResponse deleteCategory(String id) { 
         return categoryService.handleCategoryDeletion(id); 
     } 
 }
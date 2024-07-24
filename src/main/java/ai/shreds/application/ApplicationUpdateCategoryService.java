package ai.shreds.application; 
  
 import ai.shreds.domain.DomainCategoryService; 
 import ai.shreds.shared.SharedCategoryDTO; 
 import ai.shreds.shared.SharedUpdateCategoryRequest; 
 import ai.shreds.shared.SharedUpdateCategoryResponse; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import lombok.RequiredArgsConstructor; 
  
 @Service 
 @RequiredArgsConstructor 
 public class ApplicationUpdateCategoryService implements ApplicationUpdateCategoryInputPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationUpdateCategoryService.class); 
     private final DomainCategoryService categoryService; 
  
     @Override 
     public SharedCategoryDTO updateCategory(String id, String name, String description) { 
         try { 
             // Validate input data 
             validateInputData(name, description); 
             categoryService.validateCategoryData(name, description); 
             // Check if the category exists 
             boolean exists = categoryService.checkCategoryExists(id); 
             if (!exists) { 
                 throw new CategoryNotFoundException("Category not found"); 
             } 
             // Handle category update 
             return categoryService.handleCategoryUpdate(id, name, description); 
         } catch (CategoryNotFoundException e) { 
             logger.error("Category with id {} not found", id, e); 
             throw e; 
         } catch (Exception e) { 
             logger.error("Error updating category with id {}", id, e); 
             throw new RuntimeException("Error updating category", e); 
         } 
     } 
  
     private void validateInputData(String name, String description) { 
         if (name == null || name.isEmpty()) { 
             throw new InvalidInputException("Category name is required"); 
         } 
         if (description != null && description.length() > 500) { 
             throw new InvalidInputException("Category description must not exceed 500 characters"); 
         } 
     } 
 } 
 
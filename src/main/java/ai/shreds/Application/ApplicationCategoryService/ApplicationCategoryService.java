package ai.shreds.application; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainCategoryServicePort; 
 import ai.shreds.shared.SharedCreateCategoryRequestParams; 
 import ai.shreds.shared.SharedCreateCategoryResponseDTO; 
 import ai.shreds.shared.SharedUpdateCategoryRequestParams; 
 import ai.shreds.shared.SharedUpdateCategoryResponseDTO; 
 import ai.shreds.shared.SharedDeleteCategoryResponseDTO; 
 import lombok.RequiredArgsConstructor; 
 import org.springframework.stereotype.Service; 
  
 @Service 
 @RequiredArgsConstructor 
 public class ApplicationCategoryService implements ApplicationCreateCategoryInputPort, ApplicationUpdateCategoryInputPort, ApplicationDeleteCategoryInputPort { 
  
     private final DomainCategoryServicePort domainCategoryService; 
  
     @Override 
     public SharedCreateCategoryResponseDTO createCategory(SharedCreateCategoryRequestParams request) { 
         // Validate the input data 
         domainCategoryService.validateCategoryData(request); 
  
         // Ensure category name is unique 
         if (domainCategoryService.isCategoryNameExists(request.getName())) { 
             throw new IllegalArgumentException("Category name must be unique"); 
         } 
  
         // Create a new DomainCategoryEntity object from the request data 
         DomainCategoryEntity category = new DomainCategoryEntity(); 
         category.setName(request.getName()); 
         category.setDescription(request.getDescription()); 
  
         // Save the new category entity using the repository 
         DomainCategoryEntity savedCategory = domainCategoryService.save(category); 
  
         // Convert the saved entity to SharedCreateCategoryResponseDTO and return it 
         return savedCategory.toCreateCategoryResponseDTO(); 
     } 
  
     @Override 
     public SharedUpdateCategoryResponseDTO updateCategory(String id, SharedUpdateCategoryRequestParams request) { 
         // Validate the input data 
         domainCategoryService.validateCategoryData(request); 
  
         // Check if the category exists 
         DomainCategoryEntity existingCategory = domainCategoryService.checkCategoryExists(id); 
  
         // Ensure category name is unique 
         if (!existingCategory.getName().equals(request.getName()) && domainCategoryService.isCategoryNameExists(request.getName())) { 
             throw new IllegalArgumentException("Category name must be unique"); 
         } 
  
         // Update the existing DomainCategoryEntity object with the new data from the request 
         existingCategory.setName(request.getName()); 
         existingCategory.setDescription(request.getDescription()); 
  
         // Save the updated category entity using the repository 
         DomainCategoryEntity updatedCategory = domainCategoryService.save(existingCategory); 
  
         // Convert the updated entity to SharedUpdateCategoryResponseDTO and return it 
         return updatedCategory.toUpdateCategoryResponseDTO(); 
     } 
  
     @Override 
     public SharedDeleteCategoryResponseDTO deleteCategory(String id) { 
         // Check if the category exists 
         DomainCategoryEntity existingCategory = domainCategoryService.checkCategoryExists(id); 
  
         // Check if the category is associated with active products 
         if (domainCategoryService.isCategoryAssociatedWithActiveProducts(id)) { 
             throw new IllegalStateException("Cannot delete category associated with active products"); 
         } 
  
         // Delete the category entity using the repository 
         domainCategoryService.deleteById(id); 
  
         // Return a success message in SharedDeleteCategoryResponseDTO 
         SharedDeleteCategoryResponseDTO response = new SharedDeleteCategoryResponseDTO(); 
         response.setMessage("Category successfully deleted."); 
         return response; 
     } 
 } 
 
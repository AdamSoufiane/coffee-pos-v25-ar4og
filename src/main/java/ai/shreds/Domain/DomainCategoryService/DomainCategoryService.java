package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedCreateCategoryRequestParams; 
 import ai.shreds.shared.SharedCreateCategoryResponseDTO; 
 import ai.shreds.shared.SharedUpdateCategoryRequestParams; 
 import ai.shreds.shared.SharedUpdateCategoryResponseDTO; 
 import ai.shreds.shared.SharedDeleteCategoryResponseDTO; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 @Service 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class DomainCategoryService implements DomainCategoryServicePort { 
  
     @Autowired 
     private DomainCategoryRepositoryPort repository; 
  
     @Override 
     public SharedCreateCategoryResponseDTO createCategory(SharedCreateCategoryRequestParams request) { 
         // Validate input data 
         validateCategoryData(request); 
  
         // Check if category name is unique 
         if (repository.existsById(request.getName())) { 
             throw new IllegalArgumentException("Category name must be unique"); 
         } 
  
         // Create new category entity 
         DomainCategoryEntity category = new DomainCategoryEntity(); 
         category.setName(request.getName()); 
         category.setDescription(request.getDescription()); 
  
         // Save category entity 
         DomainCategoryEntity savedCategory = repository.save(category); 
  
         // Convert to response DTO and return 
         return savedCategory.toCreateCategoryResponseDTO(); 
     } 
  
     @Override 
     public SharedUpdateCategoryResponseDTO updateCategory(String id, SharedUpdateCategoryRequestParams request) { 
         // Validate input data 
         validateCategoryData(request); 
  
         // Check if category exists 
         DomainCategoryEntity existingCategory = repository.findById(id); 
         if (existingCategory == null) { 
             throw new IllegalArgumentException("Category not found"); 
         } 
  
         // Update category entity 
         existingCategory.setName(request.getName()); 
         existingCategory.setDescription(request.getDescription()); 
  
         // Save updated category entity 
         DomainCategoryEntity updatedCategory = repository.save(existingCategory); 
  
         // Convert to response DTO and return 
         return updatedCategory.toUpdateCategoryResponseDTO(); 
     } 
  
     @Override 
     public SharedDeleteCategoryResponseDTO deleteCategory(String id) { 
         // Check if category exists 
         DomainCategoryEntity existingCategory = repository.findById(id); 
         if (existingCategory == null) { 
             throw new IllegalArgumentException("Category not found"); 
         } 
  
         // Ensure category is not associated with active products (business rule) 
         if (isCategoryAssociatedWithActiveProducts(id)) { 
             throw new IllegalArgumentException("Category cannot be deleted as it is associated with active products"); 
         } 
  
         // Delete category entity 
         repository.deleteById(id); 
  
         // Return success message 
         SharedDeleteCategoryResponseDTO response = new SharedDeleteCategoryResponseDTO(); 
         response.setMessage("Category successfully deleted"); 
         return response; 
     } 
  
     private void validateCategoryData(Object request) { 
         // Implement validation logic for category data 
         if (request instanceof SharedCreateCategoryRequestParams) { 
             SharedCreateCategoryRequestParams createRequest = (SharedCreateCategoryRequestParams) request; 
             if (createRequest.getName() == null || createRequest.getName().isEmpty()) { 
                 throw new IllegalArgumentException("Category name is required"); 
             } 
             if (createRequest.getDescription() != null && createRequest.getDescription().length() > 500) { 
                 throw new IllegalArgumentException("Category description must not exceed 500 characters"); 
             } 
         } else if (request instanceof SharedUpdateCategoryRequestParams) { 
             SharedUpdateCategoryRequestParams updateRequest = (SharedUpdateCategoryRequestParams) request; 
             if (updateRequest.getName() == null || updateRequest.getName().isEmpty()) { 
                 throw new IllegalArgumentException("Category name is required"); 
             } 
             if (updateRequest.getDescription() != null && updateRequest.getDescription().length() > 500) { 
                 throw new IllegalArgumentException("Category description must not exceed 500 characters"); 
             } 
         } 
     } 
  
     private boolean isCategoryAssociatedWithActiveProducts(String categoryId) { 
         // Implement logic to check if category is associated with active products 
         return false; 
     } 
 }
package ai.shreds.application; 
  
 import ai.shreds.shared.SharedCreateCategoryRequestParams; 
 import ai.shreds.shared.SharedCreateCategoryResponseDTO; 
  
 /** 
  * Interface for creating a new category. 
  */ 
 @FunctionalInterface 
 public interface ApplicationCreateCategoryInputPort { 
     /** 
      * Creates a new category. 
      *  
      * @param request the request parameters for creating a category 
      * @return the response DTO containing the created category details 
      */ 
     SharedCreateCategoryResponseDTO createCategory(SharedCreateCategoryRequestParams request); 
 } 
 
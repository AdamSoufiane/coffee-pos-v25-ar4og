package ai.shreds.application; 
  
 import ai.shreds.shared.SharedDeleteProductResponseApplicationDTO; 
  
 /** 
  * Interface for deleting a product by its unique identifier. 
  */ 
 public interface ApplicationDeleteProductInputPort { 
     /** 
      * Deletes a product by its unique identifier. 
      * 
      * @param id the unique identifier of the product 
      * @return a response DTO containing a success message 
      */ 
     SharedDeleteProductResponseApplicationDTO deleteProduct(String id); 
 } 
 
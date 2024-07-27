package ai.shreds.application; 
  
 import ai.shreds.shared.SharedProductDTO; 
 import ai.shreds.shared.SharedSearchCriteria; 
 import java.util.List; 
  
 /** 
  * Interface for searching products based on provided search criteria. 
  */ 
 public interface ApplicationSearchProductsInputPort { 
     /** 
      * Searches for products based on provided search criteria. 
      *  
      * @param searchCriteria the criteria to search products 
      * @return a list of matching products 
      */ 
     List<SharedProductDTO> searchProducts(SharedSearchCriteria searchCriteria); 
 } 
 
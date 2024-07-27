package ai.shreds.domain; 
  
 import java.util.List; 
  
 /** 
  * Interface for searching products based on provided search criteria. 
  */ 
 public interface DomainProductSearchPort { 
     /** 
      * Searches for products based on the provided search criteria. 
      *  
      * @param searchCriteria the criteria used to search for products 
      * @return a list of products matching the search criteria 
      */ 
     List<DomainProductEntity> searchProducts(DomainSearchCriteria searchCriteria); 
 } 
 
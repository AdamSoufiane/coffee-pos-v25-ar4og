package ai.shreds.domain; 
  
 import java.util.List; 
  
 /** 
  * Interface for accessing Product entities in the Catalog Database. 
  * This interface abstracts the data access operations for Product entities. 
  */ 
 public interface DomainProductRepositoryPort { 
     /** 
      * Fetches a list of products that belong to a specific category. 
      *  
      * @param categoryId The ID of the category to retrieve products for. 
      * @return A list of DomainProductEntity objects. 
      */ 
     List<DomainProductEntity> findByCategoryId(String categoryId); 
 } 
  
 // Implementation Note: Use Lombok annotations for getters and setters in DomainProductEntity class.
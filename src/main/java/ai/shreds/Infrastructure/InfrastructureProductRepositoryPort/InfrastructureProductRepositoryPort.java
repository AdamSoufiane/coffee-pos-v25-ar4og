package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.shared.SharedListProductsParams; 
 import java.util.List; 
  
 /** 
  * Interface for accessing product data from MongoDB. 
  */ 
 public interface InfrastructureProductRepositoryPort { 
     /** 
      * Finds a product by its unique identifier. 
      * @param id the unique identifier of the product. 
      * @return the product entity. 
      */ 
     DomainProductEntity findById(String id); 
  
     /** 
      * Saves a new or updated product to the database. 
      * @param product the product entity to save. 
      */ 
     void save(DomainProductEntity product); 
  
     /** 
      * Deletes a product by its unique identifier. 
      * @param id the unique identifier of the product. 
      */ 
     void deleteById(String id); 
  
     /** 
      * Retrieves all products with optional filters for category ID, price range, and stock availability. 
      * @param params the filter parameters. 
      * @return a list of product entities. 
      */ 
     List<DomainProductEntity> findAll(SharedListProductsParams params); 
 } 
  
 // Note: Use Lombok annotations like @Data or @Getter and @Setter for DomainProductEntity. 
 // Note: Use @Repository annotation for the implementation class of this interface. 
 // Note: Ensure proper indexing on MongoDB fields like 'id' and 'categoryId' for efficient data access.
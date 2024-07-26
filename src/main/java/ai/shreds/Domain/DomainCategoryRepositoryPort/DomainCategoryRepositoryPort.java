package ai.shreds.domain; 
  
 import org.bson.types.ObjectId; 
  
 /** 
  * Interface for CRUD operations on the Category entity within the Catalog Database (MongoDB). 
  */ 
 public interface DomainCategoryRepositoryPort { 
     /** 
      * Saves a new category to the database. 
      *  
      * @param category the category entity to be saved 
      * @return the saved category entity 
      */ 
     DomainCategoryEntity save(DomainCategoryEntity category); 
  
     /** 
      * Finds a category by its unique identifier. 
      *  
      * @param id the unique identifier of the category 
      * @return the category entity if found 
      */ 
     DomainCategoryEntity findById(ObjectId id); 
  
     /** 
      * Deletes a category by its unique identifier. 
      *  
      * @param id the unique identifier of the category 
      */ 
     void deleteById(ObjectId id); 
  
     /** 
      * Checks if a category exists by its unique identifier. 
      *  
      * @param id the unique identifier of the category 
      * @return true if the category exists, false otherwise 
      */ 
     boolean existsById(ObjectId id); 
 } 
  
 // Implementation Note: Use Lombok annotations for getters and setters in DomainCategoryEntity.
package ai.shreds.domain; 
  
 import java.util.List; 
  
 /** 
  * DomainCategorySearchPort is an interface in the domain layer responsible for searching categories based on provided search criteria. 
  *  
  * Implementation Note: The implementation of this interface should handle search criteria validation and MongoDB interaction. 
  *  
  * Note: Use Lombok annotations for getters and setters if needed. 
  */ 
 public interface DomainCategorySearchPort { 
     /** 
      * Searches for categories based on the provided search criteria. 
      *  
      * @param searchCriteria the criteria to filter categories 
      * @return a list of categories matching the search criteria 
      */ 
     List<DomainCategoryEntity> searchCategories(DomainSearchCriteria searchCriteria); 
 } 
 
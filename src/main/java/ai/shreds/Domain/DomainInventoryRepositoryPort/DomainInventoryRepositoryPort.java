package ai.shreds.domain; 
  
 import ai.shreds.domain.DomainInventoryItemEntity; 
  
 /** 
  * Interface for the repository that handles inventory item data. 
  * This interface defines the contract for finding and saving inventory items. 
  */ 
 public interface DomainInventoryRepositoryPort { 
     /** 
      * Finds an inventory item by its unique identifier. 
      *  
      * @param itemId the unique identifier of the inventory item 
      * @return the inventory item entity 
      */ 
     DomainInventoryItemEntity findByItemId(String itemId); 
  
     /** 
      * Saves or updates an inventory item in the database. 
      *  
      * @param domainInventoryItemEntity the inventory item entity to be saved or updated 
      */ 
     void save(DomainInventoryItemEntity domainInventoryItemEntity); 
 }
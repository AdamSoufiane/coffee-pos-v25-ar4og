package ai.shreds.domain; 
  
 /** 
  * Interface for Inventory Service Port in the Domain Layer. 
  * This interface defines the contract for inventory-related operations. 
  */ 
 public interface DomainInventoryServicePort { 
     /** 
      * Updates the stock level of a product. 
      *  
      * @param id    the unique identifier of the product 
      * @param stock the new stock level of the product 
      * @return true if the stock update was successful, false otherwise 
      */ 
     boolean updateProductStock(String id, Integer stock); 
  
     /** 
      * Retrieves the current stock level of a product. 
      *  
      * @param id the unique identifier of the product 
      * @return the current stock level of the product 
      */ 
     Integer getProductStock(String id); 
 } 
  
 // Note: Use Lombok annotations for getters and setters.
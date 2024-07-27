package ai.shreds.domain; 
  
 import java.util.UUID; 
  
 /** 
  * This interface defines the contract for checking item availability within the inventory. 
  * Implementations of this interface will interact with the Inventory Service to verify if specific items 
  * are available and in the required quantities. 
  */ 
 public interface DomainInventoryCheckPort { 
     /** 
      * Checks the availability of an item in the inventory. 
      * 
      * @param itemId the unique identifier of the item 
      * @param quantity the quantity of the item to check 
      * @return true if the item is available in the specified quantity, false otherwise 
      */ 
     boolean checkItemAvailability(UUID itemId, int quantity); 
 }
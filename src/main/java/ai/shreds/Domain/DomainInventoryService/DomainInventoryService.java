package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedInventoryItemDTO; 
 import ai.shreds.shared.SharedInventoryStatusDTO; 
 import ai.shreds.shared.SharedUpdateStockRequestDTO; 
 import ai.shreds.shared.SharedUpdateStockResponseDTO; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.stereotype.Service; 
 import org.springframework.transaction.annotation.Transactional; 
  
 /** 
  * Service class for handling inventory-related business logic. 
  */ 
 @Slf4j 
 @Service 
 @RequiredArgsConstructor 
 public class DomainInventoryService { 
     private final DomainInventoryRepositoryPort inventoryRepository; 
     private final DomainMapperInventoryItemToDTO mapperToDTO; 
     private final DomainMapperDTOToInventoryItem mapperToEntity; 
  
     /** 
      * Checks the inventory status for a given item ID. 
      * 
      * @param itemId the unique identifier of the inventory item 
      * @return the inventory item entity 
      * @throws InventoryItemNotFoundException if the inventory item is not found 
      */ 
     @Transactional(readOnly = true) 
     public DomainInventoryItemEntity checkInventoryStatus(String itemId) { 
         log.info("Checking inventory status for itemId: {}", itemId); 
         DomainInventoryItemEntity itemEntity = inventoryRepository.findByItemId(itemId); 
         if (itemEntity == null) { 
             log.error("Inventory item not found for itemId: {}", itemId); 
             throw new InventoryItemNotFoundException("Inventory item not found"); 
         } 
         return itemEntity; 
     } 
  
     /** 
      * Processes the stock update for a given item ID and new quantity. 
      * 
      * @param itemId the unique identifier of the inventory item 
      * @param newQuantity the new quantity to update 
      * @throws InvalidQuantityException if the new quantity is negative 
      * @throws InventoryItemNotFoundException if the inventory item is not found 
      */ 
     @Transactional 
     public void processStockUpdate(String itemId, int newQuantity) { 
         if (newQuantity < 0) { 
             log.error("Invalid quantity: {} for itemId: {}", newQuantity, itemId); 
             throw new InvalidQuantityException("Quantity must be a non-negative integer"); 
         } 
         log.info("Processing stock update for itemId: {} with new quantity: {}", itemId, newQuantity); 
         DomainInventoryItemEntity itemEntity = inventoryRepository.findByItemId(itemId); 
         if (itemEntity == null) { 
             log.error("Inventory item not found for itemId: {}", itemId); 
             throw new InventoryItemNotFoundException("Inventory item not found"); 
         } 
         itemEntity.setQuantity(newQuantity); 
         inventoryRepository.save(itemEntity); 
         log.info("Stock updated successfully for itemId: {} with new quantity: {}", itemId, newQuantity); 
     } 
 } 
  
 package ai.shreds.domain.exception; 
  
 /** 
  * Exception thrown when an inventory item is not found. 
  */ 
 class InventoryItemNotFoundException extends RuntimeException { 
     public InventoryItemNotFoundException(String message) { 
         super(message); 
     } 
 } 
  
 /** 
  * Exception thrown when an invalid quantity is provided. 
  */ 
 class InvalidQuantityException extends RuntimeException { 
     public InvalidQuantityException(String message) { 
         super(message); 
     } 
 } 
 
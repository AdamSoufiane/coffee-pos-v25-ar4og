package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainInventoryItemEntity; 
 import ai.shreds.domain.DomainInventoryRepositoryPort; 
 import ai.shreds.domain.DomainMapperDTOToInventoryItem; 
 import ai.shreds.domain.DomainMapperInventoryItemToDTO; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.transaction.annotation.Transactional; 
  
 /** 
  * Implementation of the DomainInventoryRepositoryPort interface. 
  * This class interacts with the database to perform CRUD operations on InventoryItem entities. 
  */ 
 @Repository 
 @Transactional 
 public class InfrastructureInventoryRepositoryImpl implements DomainInventoryRepositoryPort { 
  
     private final JpaInventoryRepository jpaInventoryRepository; 
     private final DomainMapperDTOToInventoryItem dtoToInventoryItemMapper; 
     private final DomainMapperInventoryItemToDTO inventoryItemToDTOMapper; 
  
     @Autowired 
     public InfrastructureInventoryRepositoryImpl(JpaInventoryRepository jpaInventoryRepository, 
                                                  DomainMapperDTOToInventoryItem dtoToInventoryItemMapper, 
                                                  DomainMapperInventoryItemToDTO inventoryItemToDTOMapper) { 
         this.jpaInventoryRepository = jpaInventoryRepository; 
         this.dtoToInventoryItemMapper = dtoToInventoryItemMapper; 
         this.inventoryItemToDTOMapper = inventoryItemToDTOMapper; 
     } 
  
     /** 
      * Finds an inventory item by its unique identifier. 
      * @param itemId The unique identifier for the inventory item. 
      * @return The found DomainInventoryItemEntity or throws InventoryItemNotFoundException if not found. 
      */ 
     @Override 
     public DomainInventoryItemEntity findByItemId(String itemId) { 
         return jpaInventoryRepository.findById(itemId) 
                 .map(inventoryItem -> dtoToInventoryItemMapper.map(inventoryItem)) 
                 .orElseThrow(() -> new InventoryItemNotFoundException("Item not found: " + itemId)); 
     } 
  
     /** 
      * Saves or updates an inventory item in the database. 
      * @param domainInventoryItemEntity The inventory item entity to be saved or updated. 
      */ 
     @Override 
     public void save(DomainInventoryItemEntity domainInventoryItemEntity) { 
         try { 
             InventoryItem inventoryItem = inventoryItemToDTOMapper.map(domainInventoryItemEntity); 
             jpaInventoryRepository.save(inventoryItem); 
         } catch (Exception e) { 
             throw new InventoryPersistenceException("Failed to save inventory item", e); 
         } 
     } 
 } 
  
 import org.springframework.data.jpa.repository.JpaRepository; 
  
 /** 
  * JPA repository for InventoryItem entities. 
  */ 
 public interface JpaInventoryRepository extends JpaRepository<InventoryItem, String> { 
 } 
  
 import javax.persistence.Entity; 
 import javax.persistence.Id; 
 import lombok.Getter; 
 import lombok.Setter; 
  
 /** 
  * JPA entity representing an inventory item. 
  */ 
 @Entity 
 @Getter 
 @Setter 
 public class InventoryItem { 
  
     @Id 
     private String itemId; 
     private Integer quantity; 
     private String location; 
 } 
  
 /** 
  * Custom exception thrown when an inventory item is not found. 
  */ 
 public class InventoryItemNotFoundException extends RuntimeException { 
     public InventoryItemNotFoundException(String message) { 
         super(message); 
     } 
 } 
  
 /** 
  * Custom exception thrown when there is a persistence error with an inventory item. 
  */ 
 public class InventoryPersistenceException extends RuntimeException { 
     public InventoryPersistenceException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
 
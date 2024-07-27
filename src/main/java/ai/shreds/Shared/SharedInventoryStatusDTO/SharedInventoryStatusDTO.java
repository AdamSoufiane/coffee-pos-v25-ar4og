package ai.shreds.shared; 
  
 import lombok.Data; 
  
 /** 
  * Data Transfer Object (DTO) for transferring data related to the status of an inventory item. 
  * Contains the unique identifier, current quantity, and storage location of the item. 
  */ 
 @Data 
 public class SharedInventoryStatusDTO { 
     private String itemId; 
     private Integer quantity; 
     private String location; 
 } 
 
package ai.shreds.application; 
  
 import ai.shreds.domain.DomainInventoryItemEntity; 
 import ai.shreds.shared.SharedInventoryItemDTO; 
 import lombok.RequiredArgsConstructor; 
  
 @RequiredArgsConstructor 
 public class ApplicationMapperInventoryItemDTOToInventoryItemDomain { 
  
     public DomainInventoryItemEntity map(SharedInventoryItemDTO itemDTO) { 
         if (itemDTO == null || itemDTO.getItemId() == null || itemDTO.getQuantity() == null || itemDTO.getLocation() == null) { 
             return null; 
         } 
         return new DomainInventoryItemEntity( 
                 itemDTO.getItemId(), 
                 itemDTO.getQuantity(), 
                 itemDTO.getLocation() 
         ); 
     } 
 } 
 
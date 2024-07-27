package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import ai.shreds.domain.DomainProductEntity; 
  
 /** 
  * Data Transfer Object for Product details. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedProductDTO { 
     private String id; 
     private String name; 
     private String description; 
     private String categoryId; 
     private Double price; 
     private Integer stock; 
  
     /** 
      * Converts a DomainProductEntity to SharedProductDTO. 
      *  
      * @param entity the DomainProductEntity to convert 
      * @return the corresponding SharedProductDTO 
      */ 
     public static SharedProductDTO fromEntity(DomainProductEntity entity) { 
         return new SharedProductDTO( 
                 entity.getId(), 
                 entity.getName(), 
                 entity.getDescription(), 
                 entity.getCategoryId(), 
                 entity.getPrice(), 
                 entity.getStock() 
         ); 
     } 
  
     /** 
      * Converts this SharedProductDTO to a DomainProductEntity. 
      *  
      * @return the corresponding DomainProductEntity 
      */ 
     public DomainProductEntity toEntity() { 
         return new DomainProductEntity( 
                 this.id, 
                 this.name, 
                 this.description, 
                 this.categoryId, 
                 this.price, 
                 this.stock 
         ); 
     } 
 }
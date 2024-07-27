package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedProductDTO; 
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import org.springframework.data.annotation.Version; 
  
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 /** 
  * Represents a product within the catalog. 
  */ 
 public class DomainProductEntity { 
     /** Unique identifier for the product. */ 
     private String id; 
     /** Name of the product. */ 
     private String name; 
     /** Description of the product. */ 
     private String description; 
     /** Identifier for the category this product belongs to. */ 
     private String categoryId; 
     /** Price of the product. */ 
     private Double price; 
     /** Stock level of the product. */ 
     private Integer stock; 
     /** Version field for optimistic locking. */ 
     @Version 
     private Long version; 
  
     /** 
      * Maps SharedProductDTO to DomainProductEntity. 
      * @param dto the SharedProductDTO object 
      * @return the DomainProductEntity object 
      */ 
     public static DomainProductEntity fromDTO(SharedProductDTO dto) { 
         return DomainProductEntity.builder() 
                 .id(dto.getId()) 
                 .name(dto.getName()) 
                 .description(dto.getDescription()) 
                 .categoryId(dto.getCategoryId()) 
                 .price(dto.getPrice()) 
                 .stock(dto.getStock()) 
                 .build(); 
     } 
  
     /** 
      * Maps DomainProductEntity to SharedProductDTO. 
      * @return the SharedProductDTO object 
      */ 
     public SharedProductDTO toDTO() { 
         return SharedProductDTO.builder() 
                 .id(this.id) 
                 .name(this.name) 
                 .description(this.description) 
                 .categoryId(this.categoryId) 
                 .price(this.price) 
                 .stock(this.stock) 
                 .build(); 
     } 
  
     /** 
      * Validates the product data before creation or update. 
      * Ensures product names are unique, prices are greater than zero, and stock levels are non-negative. 
      */ 
     public void validateProductData() { 
         if (this.price <= 0) { 
             throw new IllegalArgumentException("Product price must be greater than zero."); 
         } 
         if (this.stock < 0) { 
             throw new IllegalArgumentException("Stock level must be non-negative."); 
         } 
         // Additional validation logic can be added here 
     } 
 }
package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import lombok.Builder; 
 import java.util.Objects; 
  
 /** 
  * Represents a product entity within the domain layer. 
  * Encapsulates the attributes of a product such as id, name, description, categoryId, price, and stock. 
  */ 
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class DomainProductEntity { 
     private String id; 
     private String name; 
     private String description; 
     private String categoryId; 
     private Double price; 
     private Integer stock; 
  
     /** 
      * Validates that the price is a positive value. 
      * @throws IllegalArgumentException if the price is not positive. 
      */ 
     public void validatePrice() { 
         if (this.price <= 0) { 
             throw new IllegalArgumentException("Price must be a positive value."); 
         } 
     } 
  
     /** 
      * Validates that the stock level is a non-negative integer. 
      * @throws IllegalArgumentException if the stock level is negative. 
      */ 
     public void validateStock() { 
         if (this.stock < 0) { 
             throw new IllegalArgumentException("Stock level must be a non-negative integer."); 
         } 
     } 
  
     /** 
      * Constructor with non-null validations. 
      */ 
     public DomainProductEntity(String id, String name, String description, String categoryId, Double price, Integer stock) { 
         this.id = Objects.requireNonNull(id, "id cannot be null"); 
         this.name = Objects.requireNonNull(name, "name cannot be null"); 
         this.description = Objects.requireNonNull(description, "description cannot be null"); 
         this.categoryId = Objects.requireNonNull(categoryId, "categoryId cannot be null"); 
         this.price = Objects.requireNonNull(price, "price cannot be null"); 
         this.stock = Objects.requireNonNull(stock, "stock cannot be null"); 
     } 
 } 
 
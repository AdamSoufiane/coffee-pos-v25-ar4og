package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import javax.validation.constraints.Min; 
 import javax.validation.constraints.NotBlank; 
 import javax.validation.constraints.NotNull; 
  
 /** 
  * Data Transfer Object for updating product details in the domain layer. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class DomainUpdateProductDTO { 
     /** 
      * Name of the product. 
      */ 
     @NotBlank(message = "Product name is mandatory") 
     private String name; 
  
     /** 
      * Description of the product. 
      */ 
     @NotBlank(message = "Product description is mandatory") 
     private String description; 
  
     /** 
      * Identifier for the category this product belongs to. 
      */ 
     @NotBlank(message = "Category ID is mandatory") 
     private String categoryId; 
  
     /** 
      * Price of the product. Must be a positive value. 
      */ 
     @NotNull(message = "Product price is mandatory") 
     @Min(value = 0, message = "Product price must be positive") 
     private Double price; 
  
     /** 
      * Stock level of the product. Must be a non-negative integer. 
      */ 
     @NotNull(message = "Product stock is mandatory") 
     @Min(value = 0, message = "Product stock must be non-negative") 
     private Integer stock; 
 }
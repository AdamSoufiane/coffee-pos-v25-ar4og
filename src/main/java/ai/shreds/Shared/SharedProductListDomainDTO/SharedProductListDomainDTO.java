package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Data Transfer Object (DTO) for representing a list of products in the domain layer. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedProductListDomainDTO { 
     /** 
      * Unique identifier for the product. 
      */ 
     private String id; 
  
     /** 
      * Name of the product. 
      */ 
     private String name; 
  
     /** 
      * Description of the product. 
      */ 
     private String description; 
  
     /** 
      * Identifier for the category this product belongs to. 
      */ 
     private String categoryId; 
  
     /** 
      * Price of the product. 
      */ 
     private Double price; 
  
     /** 
      * Stock level of the product. 
      */ 
     private Integer stock; 
 } 
 
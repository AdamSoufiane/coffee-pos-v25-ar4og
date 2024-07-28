package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import java.io.Serializable; 
  
 /** 
  * Data Transfer Object for Product List in the domain layer. 
  */ 
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class DomainProductListDTO implements Serializable { 
     private static final long serialVersionUID = 1L; 
  
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
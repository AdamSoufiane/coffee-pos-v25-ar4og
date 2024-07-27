package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import javax.validation.constraints.NotEmpty; 
 import javax.validation.constraints.NotNull; 
  
 /** 
  * Represents a product within the catalog. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class DomainProductEntity { 
     /** 
      * Unique identifier for the product. 
      */ 
     @NotNull 
     @NotEmpty 
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
     private double price; 
  
     /** 
      * Stock level of the product. 
      */ 
     private int stock; 
  
     @Override 
     public boolean equals(Object o) { 
         if (this == o) return true; 
         if (o == null || getClass() != o.getClass()) return false; 
         DomainProductEntity that = (DomainProductEntity) o; 
         return id.equals(that.id); 
     } 
  
     @Override 
     public int hashCode() { 
         return id.hashCode(); 
     } 
  
     @Override 
     public String toString() { 
         return "DomainProductEntity{" + 
                 "id='" + id + '\'' + 
                 ", name='" + name + '\'' + 
                 ", description='" + description + '\'' + 
                 ", categoryId='" + categoryId + '\'' + 
                 ", price=" + price + 
                 ", stock=" + stock + 
                 '}'; 
     } 
 }
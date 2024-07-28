package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class DomainProductDetailsDTO { 
     private String id; 
     private String name; 
     private String description; 
     private String categoryId; 
     private Double price; 
     private Integer stock; 
 } 
 // Note: Lombok annotations are used to generate getters, setters, and constructors automatically.
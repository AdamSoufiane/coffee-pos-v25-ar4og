package ai.shreds.shared; 
  
 import ai.shreds.domain.DomainSearchCriteria; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Value object representing search criteria for searching products and categories. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedSearchCriteria { 
     private String name; 
     private String description; 
  
     /** 
      * Maps this SharedSearchCriteria to a DomainSearchCriteria object. 
      *  
      * @return a DomainSearchCriteria object populated with values from this SharedSearchCriteria. 
      */ 
     public DomainSearchCriteria internalMapper() { 
         DomainSearchCriteria domainSearchCriteria = new DomainSearchCriteria(); 
         domainSearchCriteria.setName(this.name != null ? this.name : ""); 
         domainSearchCriteria.setDescription(this.description != null ? this.description : ""); 
         return domainSearchCriteria; 
     } 
 }
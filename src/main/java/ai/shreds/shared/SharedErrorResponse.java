package ai.shreds.shared; 
  
 /** 
  * This package contains shared Data Transfer Objects (DTOs) used across different layers of the application. 
  */ 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * A Data Transfer Object (DTO) representing an error response. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedErrorResponse { 
     /** 
      * The error message. 
      */ 
     private String error; 
 } 
 
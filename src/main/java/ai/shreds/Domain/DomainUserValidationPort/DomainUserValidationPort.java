package ai.shreds.domain; 
  
 import java.util.UUID; 
  
 /** 
  * This interface defines the contract for user validation. 
  * Implementations of this interface will interact with the User Service 
  * to validate user information. 
  */ 
 public interface DomainUserValidationPort { 
     boolean validateUser(UUID userId); 
 } 
  
 // Note: Use Lombok annotations if needed.
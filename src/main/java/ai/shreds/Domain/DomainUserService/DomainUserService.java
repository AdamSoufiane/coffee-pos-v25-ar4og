package ai.shreds.domain; 
  
 import ai.shreds.domain.DomainUserValidationPort; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.stereotype.Service; 
  
 import java.util.UUID; 
  
 @Slf4j 
 @Service 
 public class DomainUserService implements DomainUserValidationPort { 
     private final DomainUserValidationPort userValidationPort; 
  
     public DomainUserService(DomainUserValidationPort userValidationPort) { 
         this.userValidationPort = userValidationPort; 
     } 
  
     @Override 
     public boolean validateUser(UUID userId) { 
         if (userId == null) { 
             log.error("User ID is null"); 
             return false; 
         } 
  
         try { 
             boolean isValid = userValidationPort.validateUser(userId); 
             if (!isValid) { 
                 log.warn("User ID {} is not valid", userId); 
             } 
             return isValid; 
         } catch (IllegalArgumentException e) { 
             log.error("Invalid argument for user ID {}: {}", userId, e.getMessage()); 
             return false; 
         } catch (Exception e) { 
             log.error("Error validating user ID {}: {}", userId, e.getMessage()); 
             return false; 
         } 
     } 
 } 
 
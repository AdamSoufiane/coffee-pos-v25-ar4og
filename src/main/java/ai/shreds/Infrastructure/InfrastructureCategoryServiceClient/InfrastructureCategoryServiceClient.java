package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainCategoryServicePort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import org.springframework.web.client.HttpClientErrorException; 
 import org.springframework.web.client.RestTemplate; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Service 
 public class InfrastructureCategoryServiceClient implements DomainCategoryServicePort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureCategoryServiceClient.class); 
     private final RestTemplate restTemplate; 
  
     @Autowired 
     public InfrastructureCategoryServiceClient(RestTemplate restTemplate) { 
         this.restTemplate = restTemplate; 
     } 
  
     @Override 
     public boolean checkCategoryExists(String id) { 
         String url = "http://category-service/categories/" + id; 
         try { 
             restTemplate.getForEntity(url, Void.class); 
             logger.info("Category exists for ID: {}", id); 
             return true; 
         } catch (HttpClientErrorException.NotFound e) { 
             logger.warn("Category not found for ID: {}", id); 
             return false; 
         } catch (Exception e) { 
             logger.error("Error checking category for ID: {}", id, e); 
             return false; 
         } 
     } 
 } 
 
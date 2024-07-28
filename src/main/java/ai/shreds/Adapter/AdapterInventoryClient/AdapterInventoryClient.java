package ai.shreds.adapter; 
  
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.stereotype.Service; 
 import org.springframework.web.client.RestTemplate; 
 import org.springframework.http.HttpEntity; 
 import org.springframework.http.HttpMethod; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.http.HttpStatus; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Service 
 public class AdapterInventoryClient implements DomainInventoryServicePort { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterInventoryClient.class); 
     private final RestTemplate restTemplate; 
     private final String inventoryServiceBaseUrl; 
  
     public AdapterInventoryClient(RestTemplate restTemplate, @Value("${inventory.service.base.url}") String inventoryServiceBaseUrl) { 
         this.restTemplate = restTemplate; 
         this.inventoryServiceBaseUrl = inventoryServiceBaseUrl; 
     } 
  
     @Override 
     public boolean updateProductStock(String id, Integer stock) { 
         String url = inventoryServiceBaseUrl + "/inventory/products/" + id + "/stock"; 
         HttpEntity<Integer> requestEntity = new HttpEntity<>(stock); 
         try { 
             ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class); 
             if (response.getStatusCode() == HttpStatus.OK) { 
                 logger.info("Successfully updated stock for product id: {}", id); 
                 return true; 
             } else { 
                 logger.error("Failed to update stock for product id: {}. Status code: {}", id, response.getStatusCode()); 
                 return false; 
             } 
         } catch (Exception e) { 
             logger.error("Exception occurred while updating stock for product id: {}. Error: {}", id, e.getMessage()); 
             return false; 
         } 
     } 
  
     @Override 
     public Integer getProductStock(String id) { 
         String url = inventoryServiceBaseUrl + "/inventory/products/" + id + "/stock"; 
         try { 
             ResponseEntity<Integer> response = restTemplate.getForEntity(url, Integer.class); 
             if (response.getStatusCode() == HttpStatus.OK) { 
                 logger.info("Successfully retrieved stock for product id: {}", id); 
                 return response.getBody(); 
             } else { 
                 logger.error("Failed to retrieve stock for product id: {}. Status code: {}", id, response.getStatusCode()); 
                 return null; 
             } 
         } catch (Exception e) { 
             logger.error("Exception occurred while retrieving stock for product id: {}. Error: {}", id, e.getMessage()); 
             return null; 
         } 
     } 
 }
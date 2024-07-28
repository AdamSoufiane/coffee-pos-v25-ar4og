package ai.shreds.Infrastructure;

import ai.shreds.Domain.DomainInventoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class InfrastructureInventoryServiceClient implements DomainInventoryServicePort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureInventoryServiceClient.class);
    private final RestTemplate restTemplate;
    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    /**
     * Updates the stock level for a specific product.
     * @param id The unique identifier of the product.
     * @param stock The new stock level to set. Must be non-negative.
     * @return true if the stock update was successful, false otherwise.
     */
    @Override
    public boolean updateProductStock(String id, Integer stock) {
        if (stock < 0) {
            logger.error("Invalid stock level: {}. Stock level must be non-negative.", stock);
            return false;
        }
        try {
            String url = String.format("%s/products/%s/stock", inventoryServiceUrl, id);
            restTemplate.put(url, stock);
            return true;
        } catch (HttpClientErrorException e) {
            logger.error("Client error updating product stock for id {}: {}", id, e.getMessage());
            return false;
        } catch (ResourceAccessException e) {
            logger.error("Resource access error updating product stock for id {}: {}", id, e.getMessage());
            return false;
        } catch (RestClientException e) {
            logger.error("General error updating product stock for id {}: {}", id, e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves the current stock level for a specific product.
     * @param id The unique identifier of the product.
     * @return The current stock level of the product, or null if an error occurred.
     */
    @Override
    public Integer getProductStock(String id) {
        try {
            String url = String.format("%s/products/%s/stock", inventoryServiceUrl, id);
            return restTemplate.getForObject(url, Integer.class);
        } catch (HttpClientErrorException e) {
            logger.error("Client error retrieving product stock for id {}: {}", id, e.getMessage());
            return null;
        } catch (ResourceAccessException e) {
            logger.error("Resource access error retrieving product stock for id {}: {}", id, e.getMessage());
            return null;
        } catch (RestClientException e) {
            logger.error("General error retrieving product stock for id {}: {}", id, e.getMessage());
            return null;
        }
    }
}
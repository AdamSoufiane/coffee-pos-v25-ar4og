package ai.shreds.infrastructure;

import ai.shreds.domain.DomainInventoryCheckPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;

@Service
public class InfrastructureInventoryServiceClient implements DomainInventoryCheckPort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureInventoryServiceClient.class);
    private final RestTemplate restTemplate;
    private final String inventoryServiceUrl;

    public InfrastructureInventoryServiceClient(RestTemplate restTemplate, @Value("${inventory.service.url}") String inventoryServiceUrl) {
        this.restTemplate = restTemplate;
        this.inventoryServiceUrl = inventoryServiceUrl;
    }

    @Override
    public boolean checkItemAvailability(UUID itemId, Integer quantity) {
        if (itemId == null || quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid itemId or quantity");
        }
        String url = String.format("%s/api/inventory/%s/availability?quantity=%d", inventoryServiceUrl, itemId.toString(), quantity);
        try {
            Boolean isAvailable = restTemplate.getForObject(url, Boolean.class);
            if (isAvailable == null) {
                throw new InventoryServiceException("Inventory service returned null response");
            }
            return Boolean.TRUE.equals(isAvailable);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.error("Error checking item availability: {}", e.getMessage());
            throw new InventoryServiceException("Failed to check item availability", e);
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            throw new InventoryServiceException("Unexpected error occurred while checking item availability", e);
        }
    }

    public static class InventoryServiceException extends RuntimeException {
        public InventoryServiceException(String message) {
            super(message);
        }

        public InventoryServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
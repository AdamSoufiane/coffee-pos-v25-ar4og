package ai.shreds.domain;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ai.shreds.infrastructure.InventoryServiceClient;
import ai.shreds.infrastructure.InventoryServiceException;
import ai.shreds.domain.DomainInventoryCheckPort;

@Slf4j
@Service
@RequiredArgsConstructor
public class DomainInventoryService implements DomainInventoryCheckPort {
    private final InventoryServiceClient inventoryServiceClient;

    @Override
    public boolean checkItemAvailability(UUID itemId, int quantity) {
        try {
            return inventoryServiceClient.checkItemAvailability(itemId, quantity);
        } catch (InventoryServiceException e) {
            log.error("Error checking item availability for itemId: {} and quantity: {}", itemId, quantity, e);
            return false;
        }
    }
}
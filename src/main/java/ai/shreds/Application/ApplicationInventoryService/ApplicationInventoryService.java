package ai.shreds.application;

import ai.shreds.domain.DomainInventoryService;
import ai.shreds.domain.DomainInventoryItemEntity;
import ai.shreds.shared.SharedInventoryStatusDTO;
import ai.shreds.shared.SharedUpdateStockRequestDTO;
import ai.shreds.shared.SharedUpdateStockResponseDTO;
import ai.shreds.shared.SharedInventoryItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationInventoryService implements ApplicationInventoryServicePort {

    private final DomainInventoryService domainService;
    private final ApplicationMapperInventoryItemDTOToInventoryItemDomain mapperToDomain;
    private final DomainMapperInventoryItemToDTO mapperToDTO;

    @Override
    public SharedInventoryStatusDTO getInventoryStatus(String itemId) {
        if (itemId == null || itemId.isEmpty()) {
            throw new IllegalArgumentException("Item ID must not be null or empty");
        }
        DomainInventoryItemEntity domainEntity = domainService.checkInventoryStatus(itemId);
        return mapperToDTO.map(domainEntity);
    }

    @Override
    public SharedUpdateStockResponseDTO updateStockQuantity(SharedUpdateStockRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request must not be null");
        }
        if (request.getItemId() == null || request.getItemId().isEmpty()) {
            throw new IllegalArgumentException("Item ID must not be null or empty");
        }
        if (request.getNewQuantity() < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }
        domainService.processStockUpdate(request.getItemId(), request.getNewQuantity());
        DomainInventoryItemEntity updatedEntity = domainService.checkInventoryStatus(request.getItemId());
        return new SharedUpdateStockResponseDTO(updatedEntity.getItemId(), updatedEntity.getQuantity());
    }

    @Override
    public SharedInventoryItemDTO retrieveInventoryStatus(String itemId) {
        if (itemId == null || itemId.isEmpty()) {
            throw new IllegalArgumentException("Item ID must not be null or empty");
        }
        DomainInventoryItemEntity domainEntity = domainService.checkInventoryStatus(itemId);
        return mapperToDTO.map(domainEntity);
    }

    @Override
    public void updateStockQuantity(String itemId, int newQuantity) {
        if (itemId == null || itemId.isEmpty()) {
            throw new IllegalArgumentException("Item ID must not be null or empty");
        }
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }
        domainService.processStockUpdate(itemId, newQuantity);
    }
}
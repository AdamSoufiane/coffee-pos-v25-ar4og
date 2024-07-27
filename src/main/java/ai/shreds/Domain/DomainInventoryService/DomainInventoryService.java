package ai.shreds.domain;

import ai.shreds.domain.exception.InventoryItemNotFoundException;
import ai.shreds.domain.exception.InvalidQuantityException;
import ai.shreds.domain.mapper.DomainMapperDTOToInventoryItem;
import ai.shreds.domain.mapper.DomainMapperInventoryItemToDTO;
import ai.shreds.domain.repository.DomainInventoryRepositoryPort;
import ai.shreds.domain.entity.DomainInventoryItemEntity;
import ai.shreds.shared.SharedInventoryItemDTO;
import ai.shreds.shared.SharedInventoryStatusDTO;
import ai.shreds.shared.SharedUpdateStockRequestDTO;
import ai.shreds.shared.SharedUpdateStockResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for handling inventory-related business logic.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DomainInventoryService {
    private final DomainInventoryRepositoryPort inventoryRepository;
    private final DomainMapperInventoryItemToDTO mapperToDTO;
    private final DomainMapperDTOToInventoryItem mapperToEntity;

    /**
     * Checks the inventory status for a given item ID.
     *
     * @param itemId the unique identifier of the inventory item
     * @return the inventory status DTO
     * @throws InventoryItemNotFoundException if the inventory item is not found
     */
    @Transactional(readOnly = true)
    public SharedInventoryStatusDTO checkInventoryStatus(String itemId) {
        log.info("Checking inventory status for itemId: {}", itemId);
        DomainInventoryItemEntity itemEntity = inventoryRepository.findByItemId(itemId);
        if (itemEntity == null) {
            log.error("Inventory item not found for itemId: {}", itemId);
            throw new InventoryItemNotFoundException("Inventory item not found");
        }
        return new SharedInventoryStatusDTO(itemEntity.getItemId(), itemEntity.getQuantity(), itemEntity.getLocation());
    }

    /**
     * Processes the stock update for a given item ID and new quantity.
     *
     * @param request the stock update request DTO
     * @return the stock update response DTO
     * @throws InvalidQuantityException if the new quantity is negative
     * @throws InventoryItemNotFoundException if the inventory item is not found
     */
    @Transactional
    public SharedUpdateStockResponseDTO processStockUpdate(SharedUpdateStockRequestDTO request) {
        if (request.getNewQuantity() < 0) {
            log.error("Invalid quantity: {} for itemId: {}", request.getNewQuantity(), request.getItemId());
            throw new InvalidQuantityException("Quantity must be a non-negative integer");
        }
        log.info("Processing stock update for itemId: {} with new quantity: {}", request.getItemId(), request.getNewQuantity());
        DomainInventoryItemEntity itemEntity = inventoryRepository.findByItemId(request.getItemId());
        if (itemEntity == null) {
            log.error("Inventory item not found for itemId: {}", request.getItemId());
            throw new InventoryItemNotFoundException("Inventory item not found");
        }
        itemEntity.setQuantity(request.getNewQuantity());
        inventoryRepository.save(itemEntity);
        log.info("Stock updated successfully for itemId: {} with new quantity: {}", request.getItemId(), request.getNewQuantity());
        return new SharedUpdateStockResponseDTO(request.getItemId(), request.getNewQuantity());
    }

    /**
     * Retrieves the inventory status for a given item ID.
     *
     * @param itemId the unique identifier of the inventory item
     * @return the inventory item DTO
     * @throws InventoryItemNotFoundException if the inventory item is not found
     */
    public SharedInventoryItemDTO retrieveInventoryStatus(String itemId) {
        log.info("Retrieving inventory status for itemId: {}", itemId);
        DomainInventoryItemEntity itemEntity = inventoryRepository.findByItemId(itemId);
        if (itemEntity == null) {
            log.error("Inventory item not found for itemId: {}", itemId);
            throw new InventoryItemNotFoundException("Inventory item not found");
        }
        return mapperToDTO.map(itemEntity);
    }

    /**
     * Updates the stock quantity for a given item ID.
     *
     * @param itemId the unique identifier of the inventory item
     * @param newQuantity the new quantity to update
     * @throws InvalidQuantityException if the new quantity is negative
     * @throws InventoryItemNotFoundException if the inventory item is not found
     */
    @Transactional
    public void updateStockQuantity(String itemId, int newQuantity) {
        if (newQuantity < 0) {
            log.error("Invalid quantity: {} for itemId: {}", newQuantity, itemId);
            throw new InvalidQuantityException("Quantity must be a non-negative integer");
        }
        log.info("Updating stock quantity for itemId: {} to new quantity: {}", itemId, newQuantity);
        DomainInventoryItemEntity itemEntity = inventoryRepository.findByItemId(itemId);
        if (itemEntity == null) {
            log.error("Inventory item not found for itemId: {}", itemId);
            throw new InventoryItemNotFoundException("Inventory item not found");
        }
        itemEntity.setQuantity(newQuantity);
        inventoryRepository.save(itemEntity);
        log.info("Stock quantity updated successfully for itemId: {}", itemId);
    }
}
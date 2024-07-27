package ai.shreds.application;

import ai.shreds.shared.SharedInventoryStatusDTO;
import ai.shreds.shared.SharedUpdateStockRequestDTO;
import ai.shreds.shared.SharedUpdateStockResponseDTO;
import ai.shreds.shared.SharedInventoryItemDTO;

/**
 * ApplicationInventoryServicePort defines the contract for inventory-related operations in the application layer.
 */
public interface ApplicationInventoryServicePort {

    /**
     * Retrieves the current status of an inventory item, including its quantity and location.
     *
     * @param itemId the unique identifier for the inventory item
     * @return SharedInventoryStatusDTO containing the item's status
     */
    SharedInventoryStatusDTO getInventoryStatus(String itemId);

    /**
     * Updates the stock quantity of an inventory item based on incoming orders.
     *
     * @param request the request containing itemId and new quantity
     * @return SharedUpdateStockResponseDTO with the updated information
     */
    SharedUpdateStockResponseDTO updateStockQuantity(SharedUpdateStockRequestDTO request);

    /**
     * Retrieves detailed information about an inventory item.
     *
     * @param itemId the unique identifier for the inventory item
     * @return SharedInventoryItemDTO containing detailed information about the item
     */
    SharedInventoryItemDTO retrieveInventoryStatus(String itemId);

    /**
     * Updates the stock quantity of the specified inventory item.
     *
     * @param itemId the unique identifier for the inventory item
     * @param newQuantity the new quantity of the item in stock
     */
    void updateStockQuantity(String itemId, int newQuantity);
}
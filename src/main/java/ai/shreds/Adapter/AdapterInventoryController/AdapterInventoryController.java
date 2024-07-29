package ai.shreds.adapter;

import ai.shreds.application.ApplicationInventoryServicePort;
import ai.shreds.shared.SharedInventoryStatusDTO;
import ai.shreds.shared.SharedUpdateStockRequestDTO;
import ai.shreds.shared.SharedUpdateStockResponseDTO;
import ai.shreds.shared.SharedInventoryItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Controller for handling inventory-related HTTP requests.
 */
@RestController
@RequiredArgsConstructor
public class AdapterInventoryController {

    private static final Logger logger = LoggerFactory.getLogger(AdapterInventoryController.class);

    private final ApplicationInventoryServicePort inventoryService;

    /**
     * Retrieves the current status of an inventory item.
     *
     * @param itemId The unique identifier for the inventory item.
     * @return ResponseEntity containing the inventory status or a 404 status if not found.
     */
    @GetMapping("/inventory/status")
    public ResponseEntity<SharedInventoryStatusDTO> getInventoryStatus(@RequestParam String itemId) {
        if (!StringUtils.hasText(itemId)) {
            logger.error("Invalid itemId: {}", itemId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        SharedInventoryStatusDTO status = inventoryService.getInventoryStatus(itemId);
        if (status != null) {
            return new ResponseEntity<>(status, HttpStatus.OK);
        } else {
            logger.warn("Inventory item not found: {}", itemId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates the stock quantity of an inventory item based on incoming orders.
     *
     * @param request The request body containing the item ID and new quantity.
     * @return ResponseEntity containing the update response or a 400 status if the request is invalid.
     */
    @PostMapping("/inventory/update")
    public ResponseEntity<SharedUpdateStockResponseDTO> updateStockQuantity(@RequestBody SharedUpdateStockRequestDTO request) {
        if (request == null || !StringUtils.hasText(request.getItemId()) || request.getNewQuantity() == null) {
            logger.error("Invalid request data: {}", request);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            SharedUpdateStockResponseDTO response = inventoryService.updateStockQuantity(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.error("Error updating stock quantity for itemId: {}", request.getItemId(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves the current status of an inventory item.
     *
     * @param itemId The unique identifier for the inventory item.
     * @return ResponseEntity containing the inventory status or a 404 status if not found.
     */
    @GetMapping("/inventory/status/{itemId}")
    public ResponseEntity<SharedInventoryItemDTO> retrieveInventoryStatus(@PathVariable String itemId) {
        SharedInventoryItemDTO inventoryItem = inventoryService.retrieveInventoryStatus(itemId);
        if (inventoryItem != null) {
            return new ResponseEntity<>(inventoryItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates the stock quantity of an inventory item based on incoming orders.
     *
     * @param itemId The unique identifier for the inventory item.
     * @param newQuantity The new quantity of the inventory item.
     * @return ResponseEntity containing the update status or a 400 status if the request is invalid.
     */
    @PostMapping("/inventory/update/quantity")
    public ResponseEntity<Void> updateStockQuantity(@RequestParam String itemId, @RequestParam int newQuantity) {
        inventoryService.updateStockQuantity(itemId, newQuantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
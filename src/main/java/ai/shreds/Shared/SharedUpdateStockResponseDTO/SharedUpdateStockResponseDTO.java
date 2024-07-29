package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for updating stock quantities of an inventory item.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SharedUpdateStockResponseDTO {
    /**
     * Unique identifier for the inventory item.
     */
    private String itemId;

    /**
     * The updated quantity of the item in stock.
     */
    private Integer newQuantity;
}
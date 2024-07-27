package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing an inventory item.
 * Contains details such as itemId, quantity, and location.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedInventoryItemDTO {
    private String itemId;
    private Integer quantity;
    private String location;
}
package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Represents an item in the order request parameters.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharedOrderItemRequestParams {
    /**
     * Unique identifier for the item.
     */
    private UUID itemId;

    /**
     * Quantity of the item ordered.
     */
    private Integer quantity;
}
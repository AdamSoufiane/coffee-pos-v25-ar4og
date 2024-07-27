package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Represents an inventory item in the domain layer.
 * This class adheres to the business rules specified:
 * 1. An inventory item must have a unique identifier (itemId).
 * 2. The quantity of an inventory item must be a non-negative integer.
 * 3. The location of an inventory item must be specified and valid within the warehouse context.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomainInventoryItemEntity {

    /**
     * Unique identifier for the inventory item.
     */
    @NotBlank(message = "Item ID must not be blank")
    private String itemId;

    /**
     * The current quantity of the item in stock.
     */
    @NotNull(message = "Quantity must not be null")
    @Min(value = 0, message = "Quantity must be a non-negative integer")
    private Integer quantity;

    /**
     * The storage location of the item within the warehouse.
     */
    @NotBlank(message = "Location must not be blank")
    private String location;
}
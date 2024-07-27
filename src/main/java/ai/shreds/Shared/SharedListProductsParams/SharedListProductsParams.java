package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

/**
 * Value object for encapsulating parameters used to filter product listings.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedListProductsParams {
    /**
     * Optional filter by category ID.
     */
    @Size(max = 50, message = "Category ID must be less than 50 characters")
    private String categoryId;

    /**
     * Optional filter by minimum price.
     */
    @Min(value = 0, message = "Minimum price must be greater than or equal to 0")
    private Double minPrice;

    /**
     * Optional filter by maximum price.
     */
    @Min(value = 0, message = "Maximum price must be greater than or equal to 0")
    private Double maxPrice;

    /**
     * Optional filter to include only products in stock.
     */
    @NotNull(message = "In-stock filter cannot be null")
    private Boolean inStock;
}
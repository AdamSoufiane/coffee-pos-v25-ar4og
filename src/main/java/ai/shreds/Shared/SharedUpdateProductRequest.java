package ai.shreds.Shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Data Transfer Object for updating product details.
 * This class encapsulates the necessary fields required for updating a product,
 * including name, description, categoryId, price, and stock.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedUpdateProductRequest {
    @NotBlank(message = "Product name is mandatory")
    private String name;

    @NotBlank(message = "Product description is mandatory")
    private String description;

    @NotBlank(message = "Category ID is mandatory")
    private String categoryId;

    @NotNull(message = "Product price is mandatory")
    @Positive(message = "Product price must be greater than zero")
    private Double price;

    @NotNull(message = "Stock level is mandatory")
    @Min(value = 0, message = "Stock level must be non-negative")
    private Integer stock;
}
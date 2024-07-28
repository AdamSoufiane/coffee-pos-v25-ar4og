package ai.shreds.Shared;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for updating a product within the domain layer.
 * Contains fields for the product's name, description, category ID, price, and stock level.
 */
@Data
public class SharedUpdateProductDomainDTO {
    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @NotBlank(message = "Product description cannot be blank")
    private String description;

    @NotNull(message = "Category ID cannot be null")
    private String categoryId;

    @Min(value = 0, message = "Price must be a positive value")
    private Double price;

    @Min(value = 0, message = "Stock level must be a non-negative integer")
    private Integer stock;
}
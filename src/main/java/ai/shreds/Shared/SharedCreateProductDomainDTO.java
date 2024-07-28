package ai.shreds.Shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object for creating a new product in the domain layer.
 * This class encapsulates the necessary fields for creating a product,
 * such as name, description, categoryId, price, and stock.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public final class SharedCreateProductDomainDTO {

    @NotBlank(message = "Product name is mandatory")
    private String name;

    @NotBlank(message = "Product description is mandatory")
    private String description;

    @NotBlank(message = "Category ID is mandatory")
    private String categoryId;

    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price must be a positive value")
    private Double price;

    @NotNull(message = "Stock is mandatory")
    @Min(value = 0, message = "Stock must be a non-negative integer")
    private Integer stock;
}
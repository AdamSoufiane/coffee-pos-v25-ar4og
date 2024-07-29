package ai.shreds.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Data Transfer Object for creating a new product.
 * This class encapsulates the data required for creating a product,
 * including name, description, categoryId, price, and stock.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainCreateProductDTO implements Serializable {

    @NotBlank(message = "Product name must not be blank")
    private String name;

    @NotBlank(message = "Product description must not be blank")
    private String description;

    @NotBlank(message = "Category ID must not be blank")
    private String categoryId;

    @NotNull(message = "Price must not be null")
    @Min(value = 0, message = "Price must be a positive value")
    private Double price;

    @NotNull(message = "Stock must not be null")
    @Min(value = 0, message = "Stock must be a non-negative integer")
    private Integer stock;
}
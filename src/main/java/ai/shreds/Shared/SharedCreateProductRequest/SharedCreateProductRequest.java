package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharedCreateProductRequest {
    @NotBlank(message = "Product name is mandatory")
    private String name;

    private String description;

    @NotBlank(message = "Category ID is mandatory")
    private String categoryId;

    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Stock level is mandatory")
    @Min(value = 0, message = "Stock level must be non-negative")
    private Integer stock;
}
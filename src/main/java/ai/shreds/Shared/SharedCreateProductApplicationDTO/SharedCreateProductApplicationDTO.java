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
public class SharedCreateProductApplicationDTO {
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
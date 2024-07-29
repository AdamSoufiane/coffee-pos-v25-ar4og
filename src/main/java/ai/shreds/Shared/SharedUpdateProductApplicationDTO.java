package ai.shreds.Shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedUpdateProductApplicationDTO {
    @NotBlank(message = "Product name is mandatory")
    @Size(max = 255, message = "Product name must be less than 255 characters")
    private String name;

    @NotBlank(message = "Product description is mandatory")
    @Size(max = 1000, message = "Product description must be less than 1000 characters")
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
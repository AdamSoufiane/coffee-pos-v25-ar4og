package ai.shreds.Shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object for updating a category.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedUpdateCategoryRequestParams {
    /**
     * Unique identifier for the category.
     */
    @NotBlank(message = "Category ID is required.")
    private String id;

    /**
     * Name of the category.
     */
    @NotBlank(message = "Category name is required.")
    private String name;

    /**
     * Description of the category.
     */
    @Size(max = 500, message = "Description must not exceed 500 characters.")
    private String description;
}
package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object for Category.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedCategoryDTO {
    /**
     * Unique identifier for the category.
     */
    @NotNull
    @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "Invalid id format")
    private String id;

    /**
     * Name of the category.
     */
    @NotNull
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    /**
     * Description of the category.
     */
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
}
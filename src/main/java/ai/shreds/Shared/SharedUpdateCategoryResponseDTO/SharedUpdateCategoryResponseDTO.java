package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for transferring data related to the updated category.
 * Contains fields for id, name, and description of the category.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedUpdateCategoryResponseDTO {
    private String id;
    private String name;
    private String description;
}
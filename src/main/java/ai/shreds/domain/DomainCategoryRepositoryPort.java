
package ai.shreds.domain;

import ai.shreds.shared.SharedCategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Entity class representing a Category in the domain layer.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "categories")
public class DomainCategoryEntity {
    @Id
    private String id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    /**
     * Converts this entity to a SharedCategoryDTO.
     * @return a SharedCategoryDTO representation of this entity.
     */
    public SharedCategoryDTO toSharedCategoryDTO() {
        return new SharedCategoryDTO(id, name, description);
    }
}

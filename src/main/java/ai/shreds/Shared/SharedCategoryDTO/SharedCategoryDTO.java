package ai.shreds.shared;

import ai.shreds.domain.DomainCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * Data Transfer Object for Category, used to transfer data between different layers of the application.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedCategoryDTO implements Serializable {
    private static final long serialVersionUID = 1234567890L; // Unique version identifier

    /**
     * Unique identifier for the category.
     */
    private String id;

    /**
     * Name of the category.
     */
    private String name;

    /**
     * Description of the category.
     */
    private String description;

    /**
     * Maps this DTO to a DomainCategoryEntity.
     *
     * @return a DomainCategoryEntity object with the same data as this DTO.
     */
    public DomainCategoryEntity internalMapper() {
        if (this.id == null || this.name == null || this.description == null) {
            throw new IllegalArgumentException("Required fields are null in SharedCategoryDTO.");
        }
        DomainCategoryEntity domainCategoryEntity = new DomainCategoryEntity();
        domainCategoryEntity.setId(this.id);
        domainCategoryEntity.setName(this.name);
        domainCategoryEntity.setDescription(this.description);
        return domainCategoryEntity;
    }
}
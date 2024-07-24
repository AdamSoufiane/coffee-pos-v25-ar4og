
package ai.shreds.application;

import ai.shreds.domain.DomainCategoryService;
import ai.shreds.shared.SharedCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for updating a category.
 */
@Service
public class ApplicationUpdateCategoryService implements ApplicationUpdateCategoryInputPort {

    private final DomainCategoryService categoryService;

    @Autowired
    public ApplicationUpdateCategoryService(DomainCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Updates an existing category.
     *
     * @param id          the unique identifier of the category
     * @param name        the name of the category
     * @param description the description of the category
     * @return the updated category DTO
     */
    @Override
    public SharedCategoryDTO updateCategory(String id, String name, String description) {
        validateInput(id, name, description);
        return categoryService.handleCategoryUpdate(id, name, description);
    }

    /**
     * Validates the input parameters.
     *
     * @param id          the unique identifier of the category
     * @param name        the name of the category
     * @param description the description of the category
     */
    private void validateInput(String id, String name, String description) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Category ID cannot be null or empty");
        }
        if (name == null || name isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        if (description != null && description.length() > 500) {
            throw new IllegalArgumentException("Category description cannot exceed 500 characters");
        }
    }
}

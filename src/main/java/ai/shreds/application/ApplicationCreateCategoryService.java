
package ai.shreds.application;

import ai.shreds.domain.DomainCategoryService;
import ai.shreds.shared.SharedCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Service implementation for creating categories.
 */
@Service
@RequiredArgsConstructor
public class ApplicationCreateCategoryService implements ApplicationCreateCategoryInputPort {
    private final DomainCategoryService categoryService;

    /**
     * Creates a new category.
     *
     * @param name The name of the category.
     * @param description The description of the category.
     * @return The created category as a SharedCategoryDTO.
     */
    @Override
    public SharedCategoryDTO createCategory(@NotBlank @Size(max = 100) String name, @Size(max = 500) String description) {
        // Ensure that validation logic is present in the DomainCategoryService's handleCategoryCreation method.
        return categoryService.handleCategoryCreation(name, description);
    }
}

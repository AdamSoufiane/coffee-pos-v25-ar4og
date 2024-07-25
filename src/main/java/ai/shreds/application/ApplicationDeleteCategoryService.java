package ai.shreds.application;

import ai.shreds.domain.DomainCategoryService;
import ai.shreds.shared.SharedDeleteCategoryResponse;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
 * Service implementation for deleting a category.
 */
@Service
@RequiredArgsConstructor
public class ApplicationDeleteCategoryService implements ApplicationDeleteCategoryInputPort {

    private final DomainCategoryService categoryService;

    /**
     * Deletes a category by its unique identifier.
     *
     * @param id the unique identifier of the category to be deleted
     * @return response indicating the result of the deletion operation
     */
    @Override
    public SharedDeleteCategoryResponse deleteCategory(String id) {
        return categoryService.handleCategoryDeletion(id);
    }
}
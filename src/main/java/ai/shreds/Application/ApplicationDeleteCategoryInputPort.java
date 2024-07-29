package ai.shreds.Application;

import ai.shreds.Shared.SharedDeleteCategoryResponseDTO;

/**
 * ApplicationDeleteCategoryInputPort defines the contract for deleting a category within the application layer.
 */
public interface ApplicationDeleteCategoryInputPort {
    /**
     * Deletes a category by its unique identifier.
     *
     * @param id the unique identifier of the category to be deleted
     * @return a response DTO containing the result of the deletion operation
     */
    SharedDeleteCategoryResponseDTO deleteCategory(String id);
}
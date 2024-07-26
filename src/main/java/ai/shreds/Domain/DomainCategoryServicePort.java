package ai.shreds.Domain;

import ai.shreds.Shared.SharedCreateCategoryRequestParams;
import ai.shreds.Shared.SharedCreateCategoryResponseDTO;
import ai.shreds.Shared.SharedUpdateCategoryRequestParams;
import ai.shreds.Shared.SharedUpdateCategoryResponseDTO;
import ai.shreds.Shared.SharedDeleteCategoryResponseDTO;
import ai.shreds.shared.CategoryNotFoundException;

/**
 * Interface for Category Service in the Domain Layer.
 * Provides methods for creating, updating, and deleting categories.
 */
public interface DomainCategoryServicePort {

    /**
     * Creates a new category.
     *
     * @param request the request parameters for creating a category
     * @return the response DTO containing the created category details
     * @throws IllegalArgumentException if the input data is invalid
     */
    SharedCreateCategoryResponseDTO createCategory(SharedCreateCategoryRequestParams request) throws IllegalArgumentException;

    /**
     * Updates an existing category.
     *
     * @param id the unique identifier of the category to update
     * @param request the request parameters for updating the category
     * @return the response DTO containing the updated category details
     * @throws IllegalArgumentException if the input data is invalid
     * @throws CategoryNotFoundException if the category is not found
     */
    SharedUpdateCategoryResponseDTO updateCategory(String id, SharedUpdateCategoryRequestParams request) throws IllegalArgumentException, CategoryNotFoundException;

    /**
     * Deletes an existing category.
     *
     * @param id the unique identifier of the category to delete
     * @return the response DTO containing the deletion message
     * @throws CategoryNotFoundException if the category is not found
     */
    SharedDeleteCategoryResponseDTO deleteCategory(String id) throws CategoryNotFoundException;
}
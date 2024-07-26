package ai.shreds.application.port.in;

import ai.shreds.shared.SharedUpdateCategoryRequestParams;
import ai.shreds.shared.SharedUpdateCategoryResponseDTO;
import javax.validation.constraints.NotNull;

/**
 * Interface for updating a category in the catalog.
 */
public interface ApplicationUpdateCategoryInputPort {
    /**
     * Updates an existing category with the given details.
     *
     * @param id The unique identifier of the category to be updated.
     * @param request The request object containing the new category details.
     * @return The updated category data.
     */
    SharedUpdateCategoryResponseDTO updateCategory(@NotNull String id, SharedUpdateCategoryRequestParams request);
}
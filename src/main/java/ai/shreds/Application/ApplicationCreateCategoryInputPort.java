package ai.shreds.Application;

import ai.shreds.Shared.SharedCreateCategoryRequestParams;
import ai.shreds.Shared.SharedCreateCategoryResponseDTO;

/**
 * Interface for creating a new category.
 */
@FunctionalInterface
public interface ApplicationCreateCategoryInputPort {
    /**
     * Creates a new category.
     *
     * @param request the request parameters for creating a category
     * @return the response DTO containing the created category details
     */
    SharedCreateCategoryResponseDTO createCategory(SharedCreateCategoryRequestParams request);
}
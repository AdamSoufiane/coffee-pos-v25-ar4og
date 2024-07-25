package ai.shreds.application;

import ai.shreds.shared.SharedCategoryDTO;

/**
 * Interface for creating a category.
 */
public interface ApplicationCreateCategoryInputPort {
    /**
     * Creates a new category.
     *
     * @param name The name of the category.
     * @param description The description of the category.
     * @return The created category as a SharedCategoryDTO.
     */
    SharedCategoryDTO createCategory(String name, String description);
}
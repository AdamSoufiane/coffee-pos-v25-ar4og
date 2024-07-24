
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

// package-info.java
/**
 * This package contains the application layer interfaces and classes for the Category Management Shred.
 * It follows the Hexagonal Architecture principles to ensure decoupling between the business logic and external systems.
 */
package ai.shreds.application;

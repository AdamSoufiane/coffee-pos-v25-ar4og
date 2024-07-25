package ai.shreds.domain;

import ai.shreds.shared.SharedCategoryDTO;

/**
 * Interface for Category Repository operations.
 */
public interface DomainCategoryRepositoryPort {
    /**
     * Saves a category entity to the database.
     * @param category the category entity to save
     */
    void save(DomainCategoryEntity category);

    /**
     * Finds a category entity by its unique identifier.
     * @param id the unique identifier of the category
     * @return the found category entity or null if not found
     */
    DomainCategoryEntity findById(String id);

    /**
     * Deletes a category entity by its unique identifier.
     * @param id the unique identifier of the category
     */
    void deleteById(String id);

    /**
     * Checks if a category entity exists by its unique identifier.
     * @param id the unique identifier of the category
     * @return true if the category exists, false otherwise
     */
    boolean existsById(String id);
}
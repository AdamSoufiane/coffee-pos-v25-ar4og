package ai.shreds.Domain;

/**
 * Interface for checking if a category exists within the system.
 */
public interface DomainCategoryServicePort {
    /**
     * Checks if a category exists by its unique identifier.
     *
     * @param id the unique identifier of the category
     * @return true if the category exists, false otherwise
     */
    boolean checkCategoryExists(String id);
}
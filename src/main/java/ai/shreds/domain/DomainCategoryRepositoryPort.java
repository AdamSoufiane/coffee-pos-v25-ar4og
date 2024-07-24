
package ai.shreds.domain;

import ai.shreds.shared.SharedCategoryDTO;
import ai.shreds.domain.DomainCategoryEntity;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

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

/**
 * Implementation of the DomainCategoryRepositoryPort interface.
 */
@Repository
public class DomainCategoryRepository implements DomainCategoryRepositoryPort {

    private final Map<String, DomainCategoryEntity> database = new HashMap<>();

    @Override
    public void save(DomainCategoryEntity category) {
        database.put(category.getId(), category);
    }

    @Override
    public DomainCategoryEntity findById(String id) {
        return database.get(id);
    }

    @Override
    public void deleteById(String id) {
        database.remove(id);
    }

    @Override
    public boolean existsById(String id) {
        return database.containsKey(id);
    }
}

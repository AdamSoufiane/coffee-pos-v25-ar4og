package ai.shreds.infrastructure;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort {

    @Autowired
    private MongoCategoryRepository mongoCategoryRepository;

    /**
     * Saves a category to the MongoDB database.
     *
     * @param category the category entity to save
     */
    @Override
    public void save(DomainCategoryEntity category) {
        if (category == null || category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        mongoCategoryRepository.save(category);
    }

    /**
     * Finds a category by its unique identifier.
     *
     * @param id the unique identifier of the category
     * @return the found category entity or null if not found
     */
    @Override
    public DomainCategoryEntity findById(String id) {
        return mongoCategoryRepository.findById(id).orElse(null);
    }

    /**
     * Deletes a category by its unique identifier.
     *
     * @param id the unique identifier of the category to delete
     */
    @Override
    public void deleteById(String id) {
        mongoCategoryRepository.deleteById(id);
    }

    /**
     * Checks if a category exists by its unique identifier.
     *
     * @param id the unique identifier of the category
     * @return true if the category exists, false otherwise
     */
    @Override
    public boolean existsById(String id) {
        return mongoCategoryRepository.existsById(id);
    }

    /**
     * MongoDB repository for Category entity.
     */
    interface MongoCategoryRepository extends MongoRepository<DomainCategoryEntity, String> {}
}
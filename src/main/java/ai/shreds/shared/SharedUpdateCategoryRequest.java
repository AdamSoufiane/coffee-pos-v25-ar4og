
package ai.shreds.infrastructure;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@RequiredArgsConstructor
public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort {

    private final MongoCategoryRepository mongoCategoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(InfrastructureCategoryRepositoryImpl.class);

    @Override
    public void save(DomainCategoryEntity category) {
        try {
            mongoCategoryRepository.save(category);
            logger.info("Category saved: {}", category);
        } catch (Exception e) {
            logger.error("Error saving category: {}", category, e);
            throw new RuntimeException("Error saving category", e);
        }
    }

    @Override
    public DomainCategoryEntity findById(String id) {
        try {
            Optional<DomainCategoryEntity> category = mongoCategoryRepository.findById(id);
            logger.info("Category found by id: {}", id);
            return category.orElse(null);
        } catch (Exception e) {
            logger.error("Error finding category by id: {}", id, e);
            throw new RuntimeException("Error finding category by id", e);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            mongoCategoryRepository.deleteById(id);
            logger.info("Category deleted by id: {}", id);
        } catch (Exception e) {
            logger.error("Error deleting category by id: {}", id, e);
            throw new RuntimeException("Error deleting category by id", e);
        }
    }

    @Override
    public boolean existsById(String id) {
        try {
            boolean exists = mongoCategoryRepository.existsById(id);
            logger.info("Category exists by id: {} - {}", id, exists);
            return exists;
        } catch (Exception e) {
            logger.error("Error checking if category exists by id: {}", id, e);
            throw new RuntimeException("Error checking if category exists by id", e);
        }
    }

    interface MongoCategoryRepository extends MongoRepository<DomainCategoryEntity, String> {}
}



package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Request object for updating a category.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedUpdateCategoryRequest {
    /**
     * Unique identifier for the category.
     */
    @NotBlank(message = "ID cannot be blank")
    private String id;
    /**
     * Name of the category.
     */
    @NotBlank(message = "Name cannot be blank")
    private String name;
    /**
     * Description of the category.
     */
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
}

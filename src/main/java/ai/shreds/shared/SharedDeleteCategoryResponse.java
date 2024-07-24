
package ai.shreds.infrastructure;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
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

    interface MongoCategoryRepository extends MongoRepository<DomainCategoryEntity, String> {
    }
}

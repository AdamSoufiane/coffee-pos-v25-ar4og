package ai.shreds.infrastructure;

import ai.shreds.domain.CategoryAlreadyExistsException;
import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InfrastructureMongoCategoryRepositoryImpl implements DomainCategoryRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureMongoCategoryRepositoryImpl.class);

    private final InfrastructureMongoCategoryRepository categoryRepository;

    @Override
    public DomainCategoryEntity save(DomainCategoryEntity category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryAlreadyExistsException("Category name must be unique");
        }
        logger.info("Saving category: {}", category);
        return categoryRepository.save(category);
    }

    @Override
    public DomainCategoryEntity findById(ObjectId id) {
        logger.info("Finding category by id: {}", id);
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found")); // Use a more specific exception instead of RuntimeException
    }

    @Override
    public void deleteById(ObjectId id) {
        logger.info("Deleting category by id: {}", id);
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsById(ObjectId id) {
        logger.info("Checking existence of category by id: {}", id);
        return categoryRepository.existsById(id);
    }
}
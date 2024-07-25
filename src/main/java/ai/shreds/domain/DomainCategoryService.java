package ai.shreds.domain;

import ai.shreds.shared.SharedCategoryDTO;
import ai.shreds.shared.SharedDeleteCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

@Service
public class DomainCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(DomainCategoryService.class);
    private final DomainCategoryRepositoryPort categoryRepository;

    @Autowired
    public DomainCategoryService(DomainCategoryRepositoryPort categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void validateCategoryData(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Category name must not be empty");
        }
        if (description != null && description.length() > 500) {
            throw new IllegalArgumentException("Category description must not exceed 500 characters");
        }
    }

    public boolean checkCategoryExists(String id) {
        return categoryRepository.existsById(id);
    }

    public SharedCategoryDTO handleCategoryCreation(String name, String description) {
        validateCategoryData(name, description);
        DomainCategoryEntity category = new DomainCategoryEntity();
        category.setName(name);
        category.setDescription(description);
        categoryRepository.save(category);
        logger.info("Category created with name: {}", name);
        return category.toSharedCategoryDTO();
    }

    public SharedCategoryDTO handleCategoryUpdate(String id, String name, String description) {
        validateCategoryData(name, description);
        Optional<DomainCategoryEntity> optionalCategory = categoryRepository.findById(id);
        DomainCategoryEntity category = optionalCategory.orElseThrow(() -> new IllegalArgumentException("Category not found"));
        category.setName(name);
        category.setDescription(description);
        categoryRepository.save(category);
        logger.info("Category updated with id: {}", id);
        return category.toSharedCategoryDTO();
    }

    public SharedDeleteCategoryResponse handleCategoryDeletion(String id) {
        Optional<DomainCategoryEntity> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) {
            throw new IllegalArgumentException("Category not found");
        }
        categoryRepository.deleteById(id);
        SharedDeleteCategoryResponse response = new SharedDeleteCategoryResponse();
        response.setMessage("Category successfully deleted");
        logger.info("Category deleted with id: {}", id);
        return response;
    }
}
package ai.shreds.Domain;

import ai.shreds.Shared.SharedCreateCategoryRequestParams;
import ai.shreds.Shared.SharedCreateCategoryResponseDTO;
import ai.shreds.Shared.SharedUpdateCategoryRequestParams;
import ai.shreds.Shared.SharedUpdateCategoryResponseDTO;
import ai.shreds.Shared.SharedDeleteCategoryResponseDTO;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Objects;
import javax.validation.constraints.NotNull;

@Service
public class DomainCategoryService implements DomainCategoryServicePort {

    private final DomainCategoryRepositoryPort repository;

    public DomainCategoryService(DomainCategoryRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public SharedCreateCategoryResponseDTO createCategory(SharedCreateCategoryRequestParams request) {
        validateCategoryData(request);

        if (repository.existsById(request.getName())) {
            throw new IllegalArgumentException("Category name must be unique");
        }

        DomainCategoryEntity category = new DomainCategoryEntity(request.getName(), request.getDescription());
        DomainCategoryEntity savedCategory = repository.save(category);

        return savedCategory.toCreateCategoryResponseDTO();
    }

    @Override
    public SharedUpdateCategoryResponseDTO updateCategory(String id, SharedUpdateCategoryRequestParams request) {
        validateCategoryData(request);

        Optional<DomainCategoryEntity> existingCategoryOptional = repository.findById(id);
        if (!existingCategoryOptional.isPresent()) {
            throw new IllegalArgumentException("Category not found");
        }
        DomainCategoryEntity existingCategory = existingCategoryOptional.get();

        existingCategory.setName(request.getName());
        existingCategory.setDescription(request.getDescription());

        DomainCategoryEntity updatedCategory = repository.save(existingCategory);

        return updatedCategory.toUpdateCategoryResponseDTO();
    }

    @Override
    public SharedDeleteCategoryResponseDTO deleteCategory(String id) {
        Optional<DomainCategoryEntity> existingCategoryOptional = repository.findById(id);
        if (!existingCategoryOptional.isPresent()) {
            throw new IllegalArgumentException("Category not found");
        }

        if (isCategoryAssociatedWithActiveProducts(id)) {
            throw new IllegalArgumentException("Category cannot be deleted as it is associated with active products");
        }

        repository.deleteById(id);

        SharedDeleteCategoryResponseDTO response = new SharedDeleteCategoryResponseDTO();
        response.setMessage("Category successfully deleted");
        return response;
    }

    private void validateCategoryData(@NotNull Object request) {
        if (request instanceof SharedCreateCategoryRequestParams) {
            SharedCreateCategoryRequestParams createRequest = (SharedCreateCategoryRequestParams) request;
            if (Objects.isNull(createRequest.getName()) || createRequest.getName().isEmpty()) {
                throw new IllegalArgumentException("Category name is required");
            }
            if (createRequest.getDescription() != null && createRequest.getDescription().length() > 500) {
                throw new IllegalArgumentException("Category description must not exceed 500 characters");
            }
        } else if (request instanceof SharedUpdateCategoryRequestParams) {
            SharedUpdateCategoryRequestParams updateRequest = (SharedUpdateCategoryRequestParams) request;
            if (Objects.isNull(updateRequest.getName()) || updateRequest.getName().isEmpty()) {
                throw new IllegalArgumentException("Category name is required");
            }
            if (updateRequest.getDescription() != null && updateRequest.getDescription().length() > 500) {
                throw new IllegalArgumentException("Category description must not exceed 500 characters");
            }
        }
    }

    private boolean isCategoryAssociatedWithActiveProducts(String categoryId) {
        // Implement logic to check if category is associated with active products
        return false;
    }
}
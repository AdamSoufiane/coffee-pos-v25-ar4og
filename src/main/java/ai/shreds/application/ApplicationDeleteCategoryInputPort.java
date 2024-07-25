package ai.shreds.application;

import ai.shreds.domain.DomainCategoryService;
import ai.shreds.shared.SharedDeleteCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface ApplicationDeleteCategoryInputPort {
    SharedDeleteCategoryResponse deleteCategory(String id);
}

@Service
@RequiredArgsConstructor
public class ApplicationDeleteCategoryService implements ApplicationDeleteCategoryInputPort {
    private final DomainCategoryService categoryService;

    @Override
    public SharedDeleteCategoryResponse deleteCategory(String id) {
        return categoryService.handleCategoryDeletion(id);
    }
}
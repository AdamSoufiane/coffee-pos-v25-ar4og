package ai.shreds.application;

import ai.shreds.domain.DomainCategoryService;
import ai.shreds.shared.SharedDeleteCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

public interface ApplicationDeleteCategoryInputPort {
    SharedDeleteCategoryResponse deleteCategory(String id);
}

@RequiredArgsConstructor
public class ApplicationDeleteCategoryService implements ApplicationDeleteCategoryInputPort {
    private final DomainCategoryService categoryService;

    @Override
    public SharedDeleteCategoryResponse deleteCategory(String id) {
        return categoryService.handleCategoryDeletion(id);
    }
}
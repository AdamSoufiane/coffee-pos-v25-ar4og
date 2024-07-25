package ai.shreds.application;

import ai.shreds.shared.SharedCategoryDTO;

public interface ApplicationUpdateCategoryInputPort {
    SharedCategoryDTO updateCategory(String id, String name, String description);
}
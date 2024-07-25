package ai.shreds.adapter;

import ai.shreds.shared.SharedCreateCategoryRequest;
import ai.shreds.shared.SharedCreateCategoryResponse;
import ai.shreds.shared.SharedUpdateCategoryRequest;
import ai.shreds.shared.SharedUpdateCategoryResponse;
import ai.shreds.shared.SharedDeleteCategoryRequest;
import ai.shreds.shared.SharedDeleteCategoryResponse;
import ai.shreds.shared.SharedErrorResponse;
import ai.shreds.application.ApplicationCreateCategoryInputPort;
import ai.shreds.application.ApplicationUpdateCategoryInputPort;
import ai.shreds.application.ApplicationDeleteCategoryInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/categories")
public class AdapterCategoryController {

    private final ApplicationCreateCategoryInputPort createCategoryService;
    private final ApplicationUpdateCategoryInputPort updateCategoryService;
    private final ApplicationDeleteCategoryInputPort deleteCategoryService;

    @Autowired
    public AdapterCategoryController(ApplicationCreateCategoryInputPort createCategoryService,
                                     ApplicationUpdateCategoryInputPort updateCategoryService,
                                     ApplicationDeleteCategoryInputPort deleteCategoryService) {
        this.createCategoryService = createCategoryService;
        this.updateCategoryService = updateCategoryService;
        this.deleteCategoryService = deleteCategoryService;
    }

    @PostMapping
    public SharedCreateCategoryResponse createCategory(@Valid @RequestBody SharedCreateCategoryRequest request) {
        try {
            log.info("Creating category with name: {}", request.getName());
            return createCategoryService.createCategory(request.getName(), request.getDescription());
        } catch (IllegalArgumentException e) {
            log.error("Invalid input data: ", e);
            throw new RuntimeException("Invalid input data.");
        } catch (Exception e) {
            log.error("Error creating category: ", e);
            throw new RuntimeException("Internal server error.");
        }
    }

    @PutMapping("/{id}")
    public SharedUpdateCategoryResponse updateCategory(@PathVariable String id, @Valid @RequestBody SharedUpdateCategoryRequest request) {
        try {
            log.info("Updating category with id: {}", id);
            return updateCategoryService.updateCategory(id, request.getName(), request.getDescription());
        } catch (IllegalArgumentException e) {
            log.error("Invalid input data: ", e);
            throw new RuntimeException("Invalid input data.");
        } catch (Exception e) {
            log.error("Error updating category: ", e);
            throw new RuntimeException("Internal server error.");
        }
    }

    @DeleteMapping("/{id}")
    public SharedDeleteCategoryResponse deleteCategory(@PathVariable String id) {
        try {
            log.info("Deleting category with id: {}", id);
            return deleteCategoryService.deleteCategory(id);
        } catch (IllegalArgumentException e) {
            log.error("Invalid input data: ", e);
            throw new RuntimeException("Invalid input data.");
        } catch (Exception e) {
            log.error("Error deleting category: ", e);
            throw new RuntimeException("Internal server error.");
        }
    }
}
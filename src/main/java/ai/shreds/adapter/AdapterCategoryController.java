package adapter;

import shared.SharedCreateCategoryRequest;
import shared.SharedCreateCategoryResponse;
import shared.SharedUpdateCategoryRequest;
import shared.SharedUpdateCategoryResponse;
import shared.SharedDeleteCategoryRequest;
import shared.SharedDeleteCategoryResponse;
import application.ApplicationCreateCategoryInputPort;
import application.ApplicationUpdateCategoryInputPort;
import application.ApplicationDeleteCategoryInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
        validateCategoryData(request.getName(), request.getDescription());
        try {
            log.info("Creating category with name: {}", request.getName());
            return new SharedCreateCategoryResponse(
                createCategoryService.createCategory(request.getName(), request.getDescription()).getId(),
                request.getName(),
                request.getDescription()
            );
        } catch (IllegalArgumentException e) {
            log.error("Invalid input data: ", e);
            throw new InvalidInputException("Invalid input data.");
        } catch (Exception e) {
            log.error("Error creating category: ", e);
            throw new ServerErrorException("Internal server error.");
        }
    }

    @PutMapping("/{id}")
    public SharedUpdateCategoryResponse updateCategory(@PathVariable String id, @Valid @RequestBody SharedUpdateCategoryRequest request) {
        validateCategoryData(request.getName(), request.getDescription());
        try {
            log.info("Updating category with id: {}", id);
            return new SharedUpdateCategoryResponse(
                updateCategoryService.updateCategory(id, request.getName(), request.getDescription()).getId(),
                request.getName(),
                request.getDescription()
            );
        } catch (IllegalArgumentException e) {
            log.error("Invalid input data: ", e);
            throw new InvalidInputException("Invalid input data.");
        } catch (Exception e) {
            log.error("Error updating category: ", e);
            throw new ServerErrorException("Internal server error.");
        }
    }

    @DeleteMapping("/{id}")
    public SharedDeleteCategoryResponse deleteCategory(@PathVariable String id) {
        try {
            log.info("Deleting category with id: {}", id);
            return deleteCategoryService.deleteCategory(id);
        } catch (IllegalArgumentException e) {
            log.error("Invalid input data: ", e);
            throw new InvalidInputException("Invalid input data.");
        } catch (Exception e) {
            log.error("Error deleting category: ", e);
            throw new ServerErrorException("Internal server error.");
        }
    }

    private void validateCategoryData(@NotBlank String name, @Size(max = 500) String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Category name must not be null or empty.");
        }
        if (description != null && description.length() > 500) {
            throw new IllegalArgumentException("Category description must not exceed 500 characters.");
        }
    }
}
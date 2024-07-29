package ai.shreds.Adapter;

import ai.shreds.Application.ApplicationCreateCategoryInputPort;
import ai.shreds.Application.ApplicationDeleteCategoryInputPort;
import ai.shreds.Application.ApplicationUpdateCategoryInputPort;
import ai.shreds.Shared.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class AdapterCategoryController {

    private final ApplicationCreateCategoryInputPort createCategoryInputPort;
    private final ApplicationUpdateCategoryInputPort updateCategoryInputPort;
    private final ApplicationDeleteCategoryInputPort deleteCategoryInputPort;

    @PostMapping
    public ResponseEntity<SharedCreateCategoryResponseDTO> createCategory(@Valid @RequestBody SharedCreateCategoryRequestParams request) {
        SharedCreateCategoryResponseDTO response = createCategoryInputPort.createCategory(request);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SharedUpdateCategoryResponseDTO> updateCategory(@PathVariable String id, @Valid @RequestBody SharedUpdateCategoryRequestParams request) {
        SharedUpdateCategoryResponseDTO response = updateCategoryInputPort.updateCategory(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SharedDeleteCategoryResponseDTO> deleteCategory(@PathVariable String id) {
        SharedDeleteCategoryResponseDTO response = deleteCategoryInputPort.deleteCategory(id);
        return ResponseEntity.ok(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleResourceNotFoundException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
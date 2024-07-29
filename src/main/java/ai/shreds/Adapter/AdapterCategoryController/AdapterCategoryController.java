package ai.shreds.adapter;

import ai.shreds.shared.SharedCategoryDTO;
import ai.shreds.shared.SharedSearchCriteria;
import ai.shreds.shared.SharedErrorResponse;
import ai.shreds.application.ApplicationSearchCategoriesInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/categories")
public class AdapterCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(AdapterCategoryController.class);

    private final ApplicationSearchCategoriesInputPort searchCategoriesInputPort;
    private final AdapterException adapterException;

    @Autowired
    public AdapterCategoryController(ApplicationSearchCategoriesInputPort searchCategoriesInputPort, AdapterException adapterException) {
        this.searchCategoriesInputPort = searchCategoriesInputPort;
        this.adapterException = adapterException;
    }

    @PostMapping("/search")
    public ResponseEntity<List<SharedCategoryDTO>> searchCategories(@Valid @RequestBody SharedSearchCriteria searchCriteria) {
        try {
            logger.info("Searching categories with criteria: {}", searchCriteria);
            List<SharedCategoryDTO> categories = searchCategoriesInputPort.searchCategories(searchCriteria);
            return ResponseEntity.ok(categories);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid search criteria: {}", searchCriteria, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<SharedErrorResponse> handleException(Exception e) {
        logger.error("An error occurred while searching categories", e);
        SharedErrorResponse errorResponse = adapterException.handleException(e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
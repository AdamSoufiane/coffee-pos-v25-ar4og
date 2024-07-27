package ai.shreds.adapter;

import ai.shreds.application.ApplicationGetProductsByCategoryInputPort;
import ai.shreds.application.ApplicationSearchProductsInputPort;
import ai.shreds.shared.SharedProductDTO;
import ai.shreds.shared.SharedSearchCriteria;
import ai.shreds.shared.SharedErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class AdapterProductController {

    private static final Logger logger = LoggerFactory.getLogger(AdapterProductController.class);

    private final ApplicationGetProductsByCategoryInputPort getProductsByCategoryInputPort;
    private final ApplicationSearchProductsInputPort searchProductsInputPort;
    private final AdapterException adapterException;

    @Autowired
    public AdapterProductController(ApplicationGetProductsByCategoryInputPort getProductsByCategoryInputPort,
                                    ApplicationSearchProductsInputPort searchProductsInputPort,
                                    AdapterException adapterException) {
        this.getProductsByCategoryInputPort = getProductsByCategoryInputPort;
        this.searchProductsInputPort = searchProductsInputPort;
        this.adapterException = adapterException;
    }

    @GetMapping("/category")
    public ResponseEntity<List<SharedProductDTO>> getProductsByCategory(@RequestParam @Valid @NotNull String categoryId) {
        try {
            logger.info("Fetching products for category ID: {}", categoryId);
            List<SharedProductDTO> products = getProductsByCategoryInputPort.getProductsByCategory(categoryId);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching products for category ID: {}", categoryId, e);
            SharedErrorResponse errorResponse = adapterException.handleException(e);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<SharedProductDTO>> searchProducts(@RequestBody @Valid @NotNull SharedSearchCriteria searchCriteria) {
        try {
            logger.info("Searching products with criteria: {}", searchCriteria);
            List<SharedProductDTO> products = searchProductsInputPort.searchProducts(searchCriteria);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error searching products with criteria: {}", searchCriteria, e);
            SharedErrorResponse errorResponse = adapterException.handleException(e);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
package adapters.primary;

import com.example.adapters.primary.AdapterExceptionHandler;
import com.example.application.ApplicationProductServicePort;
import com.example.shared.SharedCreateProductRequest;
import com.example.shared.SharedUpdateProductRequest;
import com.example.shared.SharedListProductsParams;
import com.example.shared.SharedProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class AdapterProductController {

    private final ApplicationProductServicePort productService;
    private final AdapterExceptionHandler exceptionHandler;

    @Autowired
    public AdapterProductController(ApplicationProductServicePort productService, AdapterExceptionHandler exceptionHandler) {
        this.productService = productService;
        this.exceptionHandler = exceptionHandler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SharedProductDTO> getProductDetails(@PathVariable String id) {
        try {
            SharedProductDTO product = productService.getProductDetails(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(exceptionHandler.handleNotFoundException(e), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(exceptionHandler.handleInternalServerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<SharedProductDTO> createProductDetails(@RequestBody SharedCreateProductRequest request) {
        try {
            SharedProductDTO product = productService.createProductDetails(request);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(exceptionHandler.handleBadRequestException(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(exceptionHandler.handleInternalServerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SharedProductDTO> updateProductDetails(@PathVariable String id, @RequestBody SharedUpdateProductRequest request) {
        try {
            SharedProductDTO product = productService.updateProductDetails(id, request);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(exceptionHandler.handleNotFoundException(e), HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(exceptionHandler.handleBadRequestException(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(exceptionHandler.handleInternalServerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductDetails(@PathVariable String id) {
        try {
            productService.deleteProductDetails(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(exceptionHandler.handleNotFoundException(e), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(exceptionHandler.handleInternalServerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<SharedProductDTO>> listProducts(@RequestParam(required = false) String categoryId,
                                                               @RequestParam(required = false) Double minPrice,
                                                               @RequestParam(required = false) Double maxPrice,
                                                               @RequestParam(required = false) Boolean inStock) {
        try {
            SharedListProductsParams params = new SharedListProductsParams(categoryId, minPrice, maxPrice, inStock);
            List<SharedProductDTO> products = productService.listProducts(params);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(exceptionHandler.handleInternalServerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
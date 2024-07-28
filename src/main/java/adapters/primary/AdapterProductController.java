package adapters.primary;

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

    @Autowired
    public AdapterProductController(ApplicationProductServicePort productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SharedProductDTO> getProductDetails(@PathVariable String id) {
        SharedProductDTO product = productService.getProductDetails(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SharedProductDTO> createProductDetails(@RequestBody SharedCreateProductRequest request) {
        SharedProductDTO product = productService.createProductDetails(request);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SharedProductDTO> updateProductDetails(@PathVariable String id, @RequestBody SharedUpdateProductRequest request) {
        SharedProductDTO product = productService.updateProductDetails(id, request);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductDetails(@PathVariable String id) {
        productService.deleteProductDetails(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<SharedProductDTO>> listProducts(@RequestParam(required = false) String categoryId,
                                                               @RequestParam(required = false) Double minPrice,
                                                               @RequestParam(required = false) Double maxPrice,
                                                               @RequestParam(required = false) Boolean inStock) {
        SharedListProductsParams params = new SharedListProductsParams(categoryId, minPrice, maxPrice, inStock);
        List<SharedProductDTO> products = productService.listProducts(params);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
package ai.shreds.Adapter;

import ai.shreds.Application.ApplicationCreateProductInputPort;
import ai.shreds.Application.ApplicationDeleteProductInputPort;
import ai.shreds.Application.ApplicationGetProductDetailsInputPort;
import ai.shreds.Application.ApplicationGetProductsListInputPort;
import ai.shreds.Application.ApplicationUpdateProductInputPort;
import ai.shreds.Shared.SharedCreateProductApplicationDTO;
import ai.shreds.Shared.SharedDeleteProductResponseApplicationDTO;
import ai.shreds.Shared.SharedProductDetailsApplicationDTO;
import ai.shreds.Shared.SharedProductListApplicationDTO;
import ai.shreds.Shared.SharedUpdateProductApplicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public final class AdapterProductController {

    private final ApplicationGetProductDetailsInputPort getProductDetailsInputPort;
    private final ApplicationGetProductsListInputPort getProductsListInputPort;
    private final ApplicationCreateProductInputPort createProductInputPort;
    private final ApplicationUpdateProductInputPort updateProductInputPort;
    private final ApplicationDeleteProductInputPort deleteProductInputPort;

    @Autowired
    public AdapterProductController(ApplicationGetProductDetailsInputPort getProductDetailsInputPort,
                                     ApplicationGetProductsListInputPort getProductsListInputPort,
                                     ApplicationCreateProductInputPort createProductInputPort,
                                     ApplicationUpdateProductInputPort updateProductInputPort,
                                     ApplicationDeleteProductInputPort deleteProductInputPort) {
        this.getProductDetailsInputPort = getProductDetailsInputPort;
        this.getProductsListInputPort = getProductsListInputPort;
        this.createProductInputPort = createProductInputPort;
        this.updateProductInputPort = updateProductInputPort;
        this.deleteProductInputPort = deleteProductInputPort;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SharedProductDetailsApplicationDTO> getProductDetails(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            SharedProductDetailsApplicationDTO productDetails = getProductDetailsInputPort.getProductDetails(id);
            return ResponseEntity.ok(productDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<SharedProductListApplicationDTO>> getProductsList() {
        try {
            List<SharedProductListApplicationDTO> productsList = getProductsListInputPort.getProductsList();
            return ResponseEntity.ok(productsList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<SharedProductDetailsApplicationDTO> createProduct(@Valid @RequestBody SharedCreateProductApplicationDTO product) {
        try {
            SharedProductDetailsApplicationDTO createdProduct = createProductInputPort.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SharedProductDetailsApplicationDTO> updateProduct(@PathVariable String id, @Valid @RequestBody SharedUpdateProductApplicationDTO product) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            SharedProductDetailsApplicationDTO updatedProduct = updateProductInputPort.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SharedDeleteProductResponseApplicationDTO> deleteProduct(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            SharedDeleteProductResponseApplicationDTO response = deleteProductInputPort.deleteProduct(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
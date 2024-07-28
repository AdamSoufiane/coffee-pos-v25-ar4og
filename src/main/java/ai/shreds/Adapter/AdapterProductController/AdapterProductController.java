package ai.shreds.adapter; 
  
 import ai.shreds.application.*; 
 import ai.shreds.shared.*; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.*; 
 import javax.validation.Valid; 
 import java.util.List; 
  
 @RestController 
 @RequestMapping("/products") 
 public class AdapterProductController { 
  
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
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 
         } 
         try { 
             SharedProductDetailsApplicationDTO productDetails = getProductDetailsInputPort.getProductDetails(id); 
             return ResponseEntity.ok(productDetails); 
         } catch (Exception e) { 
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
         } 
     } 
  
     @GetMapping 
     public ResponseEntity<List<SharedProductListApplicationDTO>> getProductsList() { 
         try { 
             List<SharedProductListApplicationDTO> productsList = getProductsListInputPort.getProductsList(); 
             return ResponseEntity.ok(productsList); 
         } catch (Exception e) { 
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
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
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 
         } 
         try { 
             SharedProductDetailsApplicationDTO updatedProduct = updateProductInputPort.updateProduct(id, product); 
             return ResponseEntity.ok(updatedProduct); 
         } catch (Exception e) { 
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
         } 
     } 
  
     @DeleteMapping("/{id}") 
     public ResponseEntity<SharedDeleteProductResponseApplicationDTO> deleteProduct(@PathVariable String id) { 
         if (id == null || id.isEmpty()) { 
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 
         } 
         try { 
             SharedDeleteProductResponseApplicationDTO response = deleteProductInputPort.deleteProduct(id); 
             return ResponseEntity.ok(response); 
         } catch (Exception e) { 
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
         } 
     } 
 }
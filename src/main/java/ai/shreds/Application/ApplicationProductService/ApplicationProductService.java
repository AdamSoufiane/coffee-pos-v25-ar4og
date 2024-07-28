package ai.shreds.application; 
  
 import ai.shreds.domain.*; 
 import ai.shreds.shared.*; 
 import ai.shreds.adapter.*; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import java.util.List; 
 import java.util.stream.Collectors; 
 import lombok.RequiredArgsConstructor; 
 import org.mapstruct.factory.Mappers; 
  
 @Service 
 @RequiredArgsConstructor 
 public class ApplicationProductService implements ApplicationGetProductDetailsInputPort, ApplicationGetProductsListInputPort, ApplicationCreateProductInputPort, ApplicationUpdateProductInputPort, ApplicationDeleteProductInputPort { 
  
     private final DomainProductRepositoryPort productRepository; 
     private final DomainCategoryServicePort categoryClient; 
     private final DomainInventoryServicePort inventoryClient; 
     private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class); 
  
     @Override 
     public SharedProductDetailsApplicationDTO getProductDetails(String id) { 
         // Validate product ID 
         validateProductId(id); 
         // Fetch product details 
         DomainProductEntity product = productRepository.findById(id); 
         // Map to SharedProductDetailsApplicationDTO 
         return productMapper.toSharedProductDetailsApplicationDTO(product); 
     } 
  
     @Override 
     public List<SharedProductListApplicationDTO> getProductsList() { 
         // Fetch all products 
         List<DomainProductEntity> products = productRepository.findAll(); 
         // Map to SharedProductListApplicationDTO 
         return products.stream() 
                 .map(productMapper::toSharedProductListApplicationDTO) 
                 .collect(Collectors.toList()); 
     } 
  
     @Override 
     public SharedProductDetailsApplicationDTO createProduct(SharedCreateProductApplicationDTO productRequest) { 
         // Validate input data 
         validateProductData(productRequest); 
         // Check if category exists 
         if (!categoryClient.checkCategoryExists(productRequest.getCategoryId())) { 
             throw new IllegalArgumentException("Category does not exist"); 
         } 
         // Map to DomainCreateProductDTO 
         DomainCreateProductDTO domainProduct = productMapper.toDomainCreateProductDTO(productRequest); 
         // Save product 
         DomainProductEntity savedProduct = productRepository.save(domainProduct); 
         // Map to SharedProductDetailsApplicationDTO 
         return productMapper.toSharedProductDetailsApplicationDTO(savedProduct); 
     } 
  
     @Override 
     public SharedProductDetailsApplicationDTO updateProduct(String id, SharedUpdateProductApplicationDTO productRequest) { 
         // Validate input data and product ID 
         validateProductId(id); 
         validateProductData(productRequest); 
         // Check if product exists 
         DomainProductEntity existingProduct = productRepository.findById(id); 
         if (existingProduct == null) { 
             throw new IllegalArgumentException("Product not found"); 
         } 
         // Check if category exists 
         if (!categoryClient.checkCategoryExists(productRequest.getCategoryId())) { 
             throw new IllegalArgumentException("Category does not exist"); 
         } 
         // Map to DomainUpdateProductDTO 
         DomainUpdateProductDTO domainProduct = productMapper.toDomainUpdateProductDTO(productRequest); 
         // Update product 
         DomainProductEntity updatedProduct = productRepository.save(domainProduct); 
         // Map to SharedProductDetailsApplicationDTO 
         return productMapper.toSharedProductDetailsApplicationDTO(updatedProduct); 
     } 
  
     @Override 
     public SharedDeleteProductResponseApplicationDTO deleteProduct(String id) { 
         // Validate product ID 
         validateProductId(id); 
         // Check if product exists 
         DomainProductEntity existingProduct = productRepository.findById(id); 
         if (existingProduct == null) { 
             throw new IllegalArgumentException("Product not found"); 
         } 
         // Delete product 
         productRepository.deleteById(id); 
         // Return success message 
         SharedDeleteProductResponseApplicationDTO response = new SharedDeleteProductResponseApplicationDTO(); 
         response.setMessage("Product successfully deleted"); 
         return response; 
     } 
  
     private void validateProductId(String id) { 
         // Implement validation logic for product ID 
         if (id == null || id.isEmpty()) { 
             throw new IllegalArgumentException("Product ID cannot be null or empty"); 
         } 
     } 
  
     private void validateProductData(Object productRequest) { 
         // Implement validation logic for product data 
         if (productRequest == null) { 
             throw new IllegalArgumentException("Product data cannot be null"); 
         } 
         // Additional validation logic can be added here 
     } 
 }
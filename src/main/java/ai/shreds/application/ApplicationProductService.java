package ai.shreds.application;

import ai.shreds.domain.*;
import ai.shreds.shared.*;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationProductService implements ApplicationGetProductDetailsInputPort, ApplicationGetProductsListInputPort, ApplicationCreateProductInputPort, ApplicationUpdateProductInputPort, ApplicationDeleteProductInputPort {

    private final DomainProductRepositoryPort productRepository;
    private final DomainCategoryServicePort categoryClient;
    private final DomainInventoryServicePort inventoryClient;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public SharedProductDetailsApplicationDTO getProductDetails(String id) {
        validateProductId(id);
        DomainProductEntity product = productRepository.findById(id);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        return productMapper.toSharedProductDetailsApplicationDTO(product);
    }

    @Override
    public List<SharedProductListApplicationDTO> getProductsList() {
        List<DomainProductEntity> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toSharedProductListApplicationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SharedProductDetailsApplicationDTO createProduct(SharedCreateProductApplicationDTO productRequest) {
        validateProductData(productRequest);
        if (!categoryClient.checkCategoryExists(productRequest.getCategoryId())) {
            throw new IllegalArgumentException("Category does not exist");
        }
        DomainCreateProductDTO domainProduct = productMapper.toDomainCreateProductDTO(productRequest);
        DomainProductEntity savedProduct = productRepository.save(productMapper.toDomainProductEntity(domainProduct));
        return productMapper.toSharedProductDetailsApplicationDTO(savedProduct);
    }

    @Override
    public SharedProductDetailsApplicationDTO updateProduct(String id, SharedUpdateProductApplicationDTO productRequest) {
        validateProductId(id);
        validateProductData(productRequest);
        DomainProductEntity existingProduct = productRepository.findById(id);
        if (existingProduct == null) {
            throw new IllegalArgumentException("Product not found");
        }
        if (!categoryClient.checkCategoryExists(productRequest.getCategoryId())) {
            throw new IllegalArgumentException("Category does not exist");
        }
        DomainUpdateProductDTO domainProduct = productMapper.toDomainUpdateProductDTO(productRequest);
        DomainProductEntity updatedProduct = productRepository.save(productMapper.toDomainProductEntity(domainProduct));
        return productMapper.toSharedProductDetailsApplicationDTO(updatedProduct);
    }

    @Override
    public SharedDeleteProductResponseApplicationDTO deleteProduct(String id) {
        validateProductId(id);
        DomainProductEntity existingProduct = productRepository.findById(id);
        if (existingProduct == null) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(id);
        SharedDeleteProductResponseApplicationDTO response = new SharedDeleteProductResponseApplicationDTO();
        response.setMessage("Product successfully deleted");
        return response;
    }

    private void validateProductId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
    }

    private void validateProductData(Object productRequest) {
        if (productRequest == null) {
            throw new IllegalArgumentException("Product data cannot be null");
        }
    }
}
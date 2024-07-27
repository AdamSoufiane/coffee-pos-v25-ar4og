package ai.shreds.application;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductServicePort;
import ai.shreds.shared.SharedCreateProductRequest;
import ai.shreds.shared.SharedListProductsParams;
import ai.shreds.shared.SharedProductDTO;
import ai.shreds.shared.SharedUpdateProductRequest;
import ai.shreds.exceptions.NotFoundException;
import ai.shreds.exceptions.ProductNotFoundException;
import ai.shreds.exceptions.InvalidProductDataException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationProductServiceImpl implements ApplicationProductServicePort {

    private final DomainProductServicePort domainProductService;

    @Override
    public SharedProductDTO getProductDetails(String id) {
        try {
            log.info("Retrieving product details for ID: {}", id);
            DomainProductEntity productEntity = domainProductService.getProductDetails(id);
            return productEntity.toDTO();
        } catch (NotFoundException e) {
            log.error("Product not found with ID: {}", id, e);
            throw new ProductNotFoundException("Product not found with id: " + id, e);
        }
    }

    @Override
    public SharedProductDTO createProductDetails(SharedCreateProductRequest request) {
        validateProductData(request);
        log.info("Creating product with name: {}", request.getName());
        DomainProductEntity productEntity = DomainProductEntity.fromRequest(request);
        productEntity = domainProductService.createProductDetails(productEntity);
        return productEntity.toDTO();
    }

    @Override
    public SharedProductDTO updateProductDetails(String id, SharedUpdateProductRequest request) {
        validateProductData(request);
        try {
            log.info("Updating product with ID: {}", id);
            DomainProductEntity productEntity = DomainProductEntity.fromUpdateRequest(request);
            productEntity = domainProductService.updateProductDetails(id, productEntity);
            return productEntity.toDTO();
        } catch (NotFoundException e) {
            log.error("Product not found with ID: {}", id, e);
            throw new ProductNotFoundException("Product not found with id: " + id, e);
        }
    }

    @Override
    public void deleteProductDetails(String id) {
        try {
            log.info("Deleting product with ID: {}", id);
            domainProductService.deleteProductDetails(id);
        } catch (NotFoundException e) {
            log.error("Product not found with ID: {}", id, e);
            throw new ProductNotFoundException("Product not found with id: " + id, e);
        }
    }

    @Override
    public List<SharedProductDTO> listProducts(SharedListProductsParams params) {
        log.info("Listing products with filters: {}", params);
        List<DomainProductEntity> productEntities = domainProductService.listProducts(params);
        return productEntities.stream().map(DomainProductEntity::toDTO).collect(Collectors.toList());
    }

    private void validateProductData(Object request) {
        if (request instanceof SharedCreateProductRequest) {
            SharedCreateProductRequest createRequest = (SharedCreateProductRequest) request;
            if (createRequest.getPrice() <= 0) {
                throw new InvalidProductDataException("Product price must be greater than zero.");
            }
            if (createRequest.getStock() < 0) {
                throw new InvalidProductDataException("Stock level must be non-negative.");
            }
            if (!isProductNameUnique(createRequest.getName())) {
                throw new InvalidProductDataException("Product name must be unique within the catalog.");
            }
        } else if (request instanceof SharedUpdateProductRequest) {
            SharedUpdateProductRequest updateRequest = (SharedUpdateProductRequest) request;
            if (updateRequest.getPrice() <= 0) {
                throw new InvalidProductDataException("Product price must be greater than zero.");
            }
            if (updateRequest.getStock() < 0) {
                throw new InvalidProductDataException("Stock level must be non-negative.");
            }
            if (!isProductNameUnique(updateRequest.getName())) {
                throw new InvalidProductDataException("Product name must be unique within the catalog.");
            }
        }
    }

    private boolean isProductNameUnique(String name) {
        // Placeholder implementation
        // Implement the logic to check if the product name is unique within the catalog.
        return true;
    }
}
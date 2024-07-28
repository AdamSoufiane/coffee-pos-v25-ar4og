package ai.shreds.domain;

import ai.shreds.shared.SharedCreateProductRequest;
import ai.shreds.shared.SharedListProductsParams;
import ai.shreds.shared.SharedUpdateProductRequest;
import ai.shreds.shared.exceptions.BadRequestException;
import ai.shreds.shared.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DomainProductServiceImpl implements DomainProductServicePort {

    private final InfrastructureProductRepositoryPort repository;

    @Override
    public DomainProductEntity getProductDetails(String id) {
        DomainProductEntity product = repository.findById(id);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        return product;
    }

    @Override
    public DomainProductEntity createProductDetails(SharedCreateProductRequest request) {
        validateProductData(request);
        checkProductNameUniqueness(request.getName());
        DomainProductEntity product = SharedProductDTO.fromCreateRequest(request).toEntity();
        repository.save(product);
        return product;
    }

    @Override
    public DomainProductEntity updateProductDetails(String id, SharedUpdateProductRequest request) {
        DomainProductEntity existingProduct = repository.findById(id);
        if (existingProduct == null) {
            throw new NotFoundException("Product not found");
        }
        validateProductData(request);
        existingProduct.updateFromRequest(request.toEntity());
        repository.save(existingProduct);
        return existingProduct;
    }

    @Override
    public void deleteProductDetails(String id) {
        DomainProductEntity product = repository.findById(id);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        repository.deleteById(id);
    }

    @Override
    public List<DomainProductEntity> listProducts(SharedListProductsParams params) {
        return repository.findAll(params);
    }

    private void validateProductData(SharedCreateProductRequest request) {
        if (request.getPrice() <= 0) {
            throw new BadRequestException("Product price must be greater than zero");
        }
        if (request.getStock() < 0) {
            throw new BadRequestException("Stock level must be non-negative");
        }
    }

    private void checkProductNameUniqueness(String name) {
        if (repository.existsByName(name)) {
            throw new BadRequestException("Product name must be unique");
        }
    }
}

class DomainProductEntity {
    // Other fields and methods...

    public static DomainProductEntity fromCreateRequest(SharedCreateProductRequest request) {
        return new DomainProductEntity(
            UUID.randomUUID().toString(),
            request.getName(),
            request.getDescription(),
            request.getCategoryId(),
            request.getPrice(),
            request.getStock()
        );
    }

    public void updateFromRequest(SharedUpdateProductRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.categoryId = request.getCategoryId();
        this.price = request.getPrice();
        this.stock = request.getStock();
    }
}
package ai.shreds.Domain;

import ai.shreds.Shared.SharedCreateProductRequest;
import ai.shreds.Shared.SharedListProductsParams;
import ai.shreds.Shared.SharedUpdateProductRequest;
import ai.shreds.Shared.BadRequestException;
import ai.shreds.Shared.NotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import ai.shreds.Infrastructure.InfrastructureProductRepositoryPort;

@Service
@RequiredArgsConstructor
public class DomainProductServiceImpl implements DomainProductServicePort {

    private final InfrastructureProductRepositoryPort repository;

    @Override
    public DomainProductEntity getProductDetails(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public DomainProductEntity createProductDetails(SharedCreateProductRequest request) {
        validateProductData(request);
        checkProductNameUniqueness(request.getName());
        DomainProductEntity product = DomainProductEntity.fromRequest(request);
        repository.save(product);
        return product;
    }

    @Override
    public DomainProductEntity updateProductDetails(String id, SharedUpdateProductRequest request) {
        DomainProductEntity existingProduct = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        validateProductData(request);
        existingProduct.updateFromRequest(request);
        repository.save(existingProduct);
        return existingProduct;
    }

    @Override
    public void deleteProductDetails(String id) {
        DomainProductEntity product = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
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
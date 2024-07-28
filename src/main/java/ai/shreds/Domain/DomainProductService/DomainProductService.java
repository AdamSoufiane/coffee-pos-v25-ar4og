package ai.shreds.domain;

import ai.shreds.shared.DomainCreateProductDTO;
import ai.shreds.shared.DomainDeleteProductResponseDTO;
import ai.shreds.shared.DomainProductDetailsDTO;
import ai.shreds.shared.DomainProductEntity;
import ai.shreds.shared.DomainProductListDTO;
import ai.shreds.shared.DomainUpdateProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DomainProductService {
    private final DomainProductRepositoryPort productRepository;
    private final DomainCategoryServicePort categoryService;
    private final DomainInventoryServicePort inventoryService;
    private static final Logger logger = LoggerFactory.getLogger(DomainProductService.class);

    public DomainProductDetailsDTO getProductDetails(String id) {
        if (productRepository == null || categoryService == null || inventoryService == null) {
            throw new IllegalStateException("Dependencies not properly injected");
        }
        DomainProductEntity productEntity = productRepository.findById(id);
        if (productEntity == null) {
            throw new ProductNotFoundException("Product not found");
        }
        logger.info("Product details retrieved for id: {}", id);
        return mapToProductDetailsDTO(productEntity);
    }

    public List<DomainProductListDTO> getProductsList() {
        if (productRepository == null || categoryService == null || inventoryService == null) {
            throw new IllegalStateException("Dependencies not properly injected");
        }
        List<DomainProductEntity> productEntities = productRepository.findAll();
        logger.info("Product list retrieved, total products: {}", productEntities.size());
        return productEntities.stream().map(this::mapToProductListDTO).collect(Collectors.toList());
    }

    @Transactional
    public DomainProductDetailsDTO createProduct(DomainCreateProductDTO productDTO) {
        if (productRepository == null || categoryService == null || inventoryService == null) {
            throw new IllegalStateException("Dependencies not properly injected");
        }
        validateProductData(productDTO);
        if (!categoryService.checkCategoryExists(productDTO.getCategoryId())) {
            throw new CategoryNotFoundException("Category not found");
        }
        DomainProductEntity productEntity = mapToProductEntity(productDTO);
        productEntity = productRepository.save(productEntity);
        inventoryService.updateProductStock(productEntity.getId(), productEntity.getStock());
        logger.info("Product created with id: {}", productEntity.getId());
        return mapToProductDetailsDTO(productEntity);
    }

    @Transactional
    public DomainProductDetailsDTO updateProduct(String id, DomainUpdateProductDTO productDTO) {
        if (productRepository == null || categoryService == null || inventoryService == null) {
            throw new IllegalStateException("Dependencies not properly injected");
        }
        validateProductData(productDTO);
        DomainProductEntity existingProduct = productRepository.findById(id);
        if (existingProduct == null) {
            throw new ProductNotFoundException("Product not found");
        }
        if (!categoryService.checkCategoryExists(productDTO.getCategoryId())) {
            throw new CategoryNotFoundException("Category not found");
        }
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setCategoryId(productDTO.getCategoryId());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setStock(productDTO.getStock());
        productRepository.save(existingProduct);
        inventoryService.updateProductStock(existingProduct.getId(), existingProduct.getStock());
        logger.info("Product updated with id: {}", existingProduct.getId());
        return mapToProductDetailsDTO(existingProduct);
    }

    public DomainDeleteProductResponseDTO deleteProduct(String id) {
        if (productRepository == null || categoryService == null || inventoryService == null) {
            throw new IllegalStateException("Dependencies not properly injected");
        }
        DomainProductEntity existingProduct = productRepository.findById(id);
        if (existingProduct == null) {
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
        logger.info("Product deleted with id: {}", id);
        return new DomainDeleteProductResponseDTO("Product successfully deleted");
    }

    private void validateProductData(DomainCreateProductDTO productDTO) {
        if (productDTO.getName() == null || productDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (productDTO.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be positive");
        }
        if (productDTO.getStock() < 0) {
            throw new IllegalArgumentException("Product stock must be non-negative");
        }
    }

    private void validateProductData(DomainUpdateProductDTO productDTO) {
        if (productDTO.getName() == null || productDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (productDTO.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be positive");
        }
        if (productDTO.getStock() < 0) {
            throw new IllegalArgumentException("Product stock must be non-negative");
        }
    }

    private DomainProductDetailsDTO mapToProductDetailsDTO(DomainProductEntity entity) {
        return new DomainProductDetailsDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCategoryId(),
                entity.getPrice(),
                entity.getStock()
        );
    }

    private DomainProductListDTO mapToProductListDTO(DomainProductEntity entity) {
        return new DomainProductListDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCategoryId(),
                entity.getPrice(),
                entity.getStock()
        );
    }

    private DomainProductEntity mapToProductEntity(DomainCreateProductDTO dto) {
        return new DomainProductEntity(
                null,
                dto.getName(),
                dto.getDescription(),
                dto.getCategoryId(),
                dto.getPrice(),
                dto.getStock()
        );
    }
}
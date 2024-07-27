package ai.shreds.domain;

import ai.shreds.shared.SharedProductDTO;
import ai.shreds.shared.SharedCategoryDTO;
import ai.shreds.shared.SharedSearchCriteria;
import ai.shreds.infrastructure.InfrastructureMongoProductRepositoryImpl;
import ai.shreds.infrastructure.InfrastructureMongoProductSearchImpl;
import ai.shreds.infrastructure.InfrastructureMongoCategorySearchImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainCategoryEntity;

@Slf4j
@Service
@RequiredArgsConstructor
public class DomainCatalogQueryService implements ApplicationGetProductsByCategoryInputPort, ApplicationSearchProductsInputPort, ApplicationSearchCategoriesInputPort {

    private final InfrastructureMongoProductRepositoryImpl productRepository;
    private final InfrastructureMongoProductSearchImpl productSearch;
    private final InfrastructureMongoCategorySearchImpl categorySearch;

    @Override
    public List<SharedProductDTO> getProductsByCategory(String categoryId) {
        try {
            List<DomainProductEntity> products = productRepository.findByCategoryId(categoryId);
            return products.stream().map(DomainProductEntity::internalMapper).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error fetching products by category ID: {}", categoryId, e);
            throw new RuntimeException("Error fetching products by category ID", e);
        }
    }

    @Override
    public List<SharedProductDTO> searchProducts(SharedSearchCriteria searchCriteria) {
        try {
            List<DomainProductEntity> products = productSearch.searchProducts(searchCriteria.internalMapper());
            return products.stream().map(DomainProductEntity::internalMapper).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error searching products with criteria: {}", searchCriteria, e);
            throw new RuntimeException("Error searching products", e);
        }
    }

    @Override
    public List<SharedCategoryDTO> searchCategories(SharedSearchCriteria searchCriteria) {
        try {
            List<DomainCategoryEntity> categories = categorySearch.searchCategories(searchCriteria.internalMapper());
            return categories.stream().map(DomainCategoryEntity::internalMapper).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error searching categories with criteria: {}", searchCriteria, e);
            throw new RuntimeException("Error searching categories", e);
        }
    }
}
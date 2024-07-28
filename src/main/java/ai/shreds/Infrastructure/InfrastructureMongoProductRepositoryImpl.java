package ai.shreds.Infrastructure;

import ai.shreds.Domain.DomainProductEntity;
import ai.shreds.Shared.SharedListProductsParams;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import java.util.List;
import javax.validation.constraints.NotNull;

@Repository
@Validated
public class InfrastructureMongoProductRepositoryImpl implements InfrastructureProductRepositoryPort {

    private final MongoTemplate mongoTemplate;

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureMongoProductRepositoryImpl.class);

    public InfrastructureMongoProductRepositoryImpl(@NotNull MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Finds a product by its ID.
     * @param id The ID of the product.
     * @return The found DomainProductEntity or null if not found.
     */
    @Override
    public DomainProductEntity findById(@NotNull String id) {
        try {
            return mongoTemplate.findById(id, DomainProductEntity.class);
        } catch (Exception e) {
            logger.error("Error finding product by id: {}", id, e);
            throw new RuntimeException("Error finding product by id", e);
        }
    }

    /**
     * Saves a product entity to the database.
     * @param product The product entity to save.
     */
    @Override
    public void save(@NotNull DomainProductEntity product) {
        try {
            validateProductData(product);
            mongoTemplate.save(product);
            logger.info("Product saved: {}", product);
        } catch (Exception e) {
            logger.error("Error saving product: {}", product, e);
            throw new RuntimeException("Error saving product", e);
        }
    }

    /**
     * Deletes a product by its ID.
     * @param id The ID of the product to delete.
     */
    @Override
    public void deleteById(@NotNull String id) {
        try {
            Query query = new Query(Criteria.where("id").is(id));
            mongoTemplate.remove(query, DomainProductEntity.class);
            logger.info("Product deleted by id: {}", id);
        } catch (Exception e) {
            logger.error("Error deleting product by id: {}", id, e);
            throw new RuntimeException("Error deleting product by id", e);
        }
    }

    /**
     * Finds all products matching the given parameters.
     * @param params The parameters to filter the products.
     * @return A list of DomainProductEntities matching the parameters.
     */
    @Override
    public List<DomainProductEntity> findAll(@NotNull SharedListProductsParams params) {
        try {
            Query query = new Query();
            if (params.getCategoryId() != null) {
                query.addCriteria(Criteria.where("categoryId").is(params.getCategoryId()));
            }
            if (params.getMinPrice() != null) {
                query.addCriteria(Criteria.where("price").gte(params.getMinPrice()));
            }
            if (params.getMaxPrice() != null) {
                query.addCriteria(Criteria.where("price").lte(params.getMaxPrice()));
            }
            if (params.getInStock() != null && params.getInStock()) {
                query.addCriteria(Criteria.where("stock").gt(0));
            }
            return mongoTemplate.find(query, DomainProductEntity.class);
        } catch (Exception e) {
            logger.error("Error finding products with params: {}", params, e);
            throw new RuntimeException("Error finding products", e);
        }
    }

    /**
     * Validates the product data before saving.
     * @param product The product entity to validate.
     */
    private void validateProductData(DomainProductEntity product) {
        if (StringUtils.isEmpty(product.getName())) {
            throw new IllegalArgumentException("Product name must not be empty");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Product stock must be non-negative");
        }
        // Ensure product name is unique
        Query query = new Query(Criteria.where("name").is(product.getName()));
        if (mongoTemplate.exists(query, DomainProductEntity.class)) {
            throw new IllegalArgumentException("Product name must be unique");
        }
    }
}
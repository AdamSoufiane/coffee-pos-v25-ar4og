package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductSearchPort;
import ai.shreds.domain.DomainSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Objects;

@Repository
public class InfrastructureMongoProductSearchImpl implements DomainProductSearchPort {
    private static final Logger logger = LoggerFactory.getLogger(InfrastructureMongoProductSearchImpl.class);
    private final MongoTemplate mongoTemplate;

    @Autowired
    public InfrastructureMongoProductSearchImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<DomainProductEntity> searchProducts(DomainSearchCriteria searchCriteria) {
        Objects.requireNonNull(searchCriteria, "Search criteria must not be null.");
        if (searchCriteria.getName() == null && searchCriteria.getDescription() == null) {
            throw new IllegalArgumentException("Search criteria must contain at least one non-null field.");
        }

        Query query = new Query();

        if (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()) {
            query.addCriteria(Criteria.where("name").regex(searchCriteria.getName().toLowerCase(), "i"));
        }

        if (searchCriteria.getDescription() != null && !searchCriteria.getDescription().isEmpty()) {
            query.addCriteria(Criteria.where("description").regex(searchCriteria.getDescription().toLowerCase(), "i"));
        }

        Pageable pageableRequest = PageRequest.of(0, 20); // Default to first page with 20 items per page
        query.with(pageableRequest);

        try {
            logger.info("Executing product search with criteria: {}", searchCriteria);
            List<DomainProductEntity> productResults = mongoTemplate.find(query, DomainProductEntity.class);
            logger.info("Found {} products matching the criteria.", productResults.size());
            return productResults;
        } catch (Exception e) {
            logger.error("Error occurred while searching for products: ", e);
            throw new RuntimeException("Database error occurred while searching for products.", e);
        }
    }
}
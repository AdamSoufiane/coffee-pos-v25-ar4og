package ai.shreds.infrastructure;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategorySearchPort;
import ai.shreds.domain.DomainSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

@Repository
@RequiredArgsConstructor
public class InfrastructureMongoCategorySearchImpl implements DomainCategorySearchPort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureMongoCategorySearchImpl.class);
    private final MongoTemplate mongoTemplate;

    @Override
    public List<DomainCategoryEntity> searchCategories(DomainSearchCriteria searchCriteria) {
        if (searchCriteria == null) {
            throw new IllegalArgumentException("searchCriteria cannot be null");
        }
        logger.info("Starting search for categories with criteria: {}", searchCriteria);
        Query query = new Query();

        if (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()) {
            query.addCriteria(Criteria.where("name").regex(Pattern.compile(searchCriteria.getName(), Pattern.CASE_INSENSITIVE)));
        }
        if (searchCriteria.getDescription() != null && !searchCriteria.getDescription().isEmpty()) {
            query.addCriteria(Criteria.where("description").regex(Pattern.compile(searchCriteria.getDescription(), Pattern.CASE_INSENSITIVE)));
        }

        // Adding pagination support
        int page = searchCriteria.getPage() != null ? searchCriteria.getPage() : 0;
        int size = searchCriteria.getSize() != null ? searchCriteria.getSize() : 10;
        Pageable pageable = PageRequest.of(page, size);
        query.with(pageable);

        List<DomainCategoryEntity> results = mongoTemplate.find(query, DomainCategoryEntity.class);
        logger.info("Found {} categories matching criteria", results.size());
        return results;
    }
}
package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.domain.DomainProductRepositoryPort; 
 import lombok.RequiredArgsConstructor; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.data.mongodb.core.MongoTemplate; 
 import org.springframework.data.mongodb.core.query.Criteria; 
 import org.springframework.data.mongodb.core.query.Query; 
 import org.springframework.stereotype.Repository; 
 import java.util.List; 
  
 @Repository 
 @RequiredArgsConstructor 
 public class InfrastructureMongoProductRepositoryImpl implements DomainProductRepositoryPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureMongoProductRepositoryImpl.class); 
     private final MongoTemplate mongoTemplate; 
  
     @Override 
     public List<DomainProductEntity> findByCategoryId(String categoryId) { 
         try { 
             Query query = new Query(); 
             query.addCriteria(Criteria.where("categoryId").is(categoryId)); 
             return mongoTemplate.find(query, DomainProductEntity.class); 
         } catch (Exception e) { 
             logger.error("Error fetching products by category ID", e); 
             throw new RuntimeException("Error fetching products by category ID", e); 
         } 
     } 
 } 
 
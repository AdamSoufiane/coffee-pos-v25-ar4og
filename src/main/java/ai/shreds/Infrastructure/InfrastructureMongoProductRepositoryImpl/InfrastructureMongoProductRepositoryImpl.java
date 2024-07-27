package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.shared.SharedListProductsParams; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.data.mongodb.core.MongoTemplate; 
 import org.springframework.data.mongodb.core.query.Criteria; 
 import org.springframework.data.mongodb.core.query.Query; 
 import org.springframework.stereotype.Repository; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import java.util.List; 
 import java.util.Optional; 
  
 @Repository 
 public class InfrastructureMongoProductRepositoryImpl implements InfrastructureProductRepositoryPort { 
  
     @Autowired 
     private MongoTemplate mongoTemplate; 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureMongoProductRepositoryImpl.class); 
  
     @Override 
     public DomainProductEntity findById(String id) { 
         try { 
             return mongoTemplate.findById(id, DomainProductEntity.class); 
         } catch (Exception e) { 
             logger.error("Error finding product by id: {}", id, e); 
             throw new RuntimeException("Error finding product by id", e); 
         } 
     } 
  
     @Override 
     public void save(DomainProductEntity product) { 
         try { 
             validateProductData(product); 
             mongoTemplate.save(product); 
             logger.info("Product saved: {}", product); 
         } catch (Exception e) { 
             logger.error("Error saving product: {}", product, e); 
             throw new RuntimeException("Error saving product", e); 
         } 
     } 
  
     @Override 
     public void deleteById(String id) { 
         try { 
             Query query = new Query(Criteria.where("id").is(id)); 
             mongoTemplate.remove(query, DomainProductEntity.class); 
             logger.info("Product deleted by id: {}", id); 
         } catch (Exception e) { 
             logger.error("Error deleting product by id: {}", id, e); 
             throw new RuntimeException("Error deleting product by id", e); 
         } 
     } 
  
     @Override 
     public List<DomainProductEntity> findAll(SharedListProductsParams params) { 
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
             if (params.getInStock() != null) { 
                 query.addCriteria(Criteria.where("stock").gt(0)); 
             } 
             return mongoTemplate.find(query, DomainProductEntity.class); 
         } catch (Exception e) { 
             logger.error("Error finding products with params: {}", params, e); 
             throw new RuntimeException("Error finding products", e); 
         } 
     } 
  
     private void validateProductData(DomainProductEntity product) { 
         if (product.getName() == null || product.getName().isEmpty()) { 
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
 
package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import org.bson.types.ObjectId; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.data.mongodb.repository.MongoRepository; 
 import org.springframework.stereotype.Repository; 
 import lombok.RequiredArgsConstructor; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @RequiredArgsConstructor 
 @Repository 
 public class InfrastructureMongoCategoryRepositoryImpl implements DomainCategoryRepositoryPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureMongoCategoryRepositoryImpl.class); 
  
     private final InfrastructureMongoCategoryRepository categoryRepository; 
  
     @Override 
     public DomainCategoryEntity save(DomainCategoryEntity category) { 
         try { 
             DomainCategoryEntity savedCategory = categoryRepository.save(category); 
             logger.info("Category saved successfully: {}", savedCategory); 
             return savedCategory; 
         } catch (Exception e) { 
             logger.error("Error saving category", e); 
             throw new RuntimeException("Error saving category", e); 
         } 
     } 
  
     @Override 
     public DomainCategoryEntity findById(ObjectId id) { 
         try { 
             DomainCategoryEntity category = categoryRepository.findById(id).orElse(null); 
             if (category != null) { 
                 logger.info("Category found: {}", category); 
             } else { 
                 logger.warn("Category not found with id: {}", id); 
             } 
             return category; 
         } catch (Exception e) { 
             logger.error("Error finding category by id", e); 
             throw new RuntimeException("Error finding category by id", e); 
         } 
     } 
  
     @Override 
     public void deleteById(ObjectId id) { 
         try { 
             categoryRepository.deleteById(id); 
             logger.info("Category deleted successfully with id: {}", id); 
         } catch (Exception e) { 
             logger.error("Error deleting category by id", e); 
             throw new RuntimeException("Error deleting category by id", e); 
         } 
     } 
  
     @Override 
     public boolean existsById(ObjectId id) { 
         try { 
             boolean exists = categoryRepository.existsById(id); 
             logger.info("Category exists with id {}: {}", id, exists); 
             return exists; 
         } catch (Exception e) { 
             logger.error("Error checking if category exists by id", e); 
             throw new RuntimeException("Error checking if category exists by id", e); 
         } 
     } 
 } 
  
 public interface InfrastructureMongoCategoryRepository extends MongoRepository<DomainCategoryEntity, ObjectId> { 
     // Define methods for MongoDB operations if needed 
 }
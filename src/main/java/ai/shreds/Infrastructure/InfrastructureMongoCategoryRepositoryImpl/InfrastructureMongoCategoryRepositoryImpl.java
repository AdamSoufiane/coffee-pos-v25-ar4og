package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import java.util.Optional; 
 import org.bson.types.ObjectId; 
 import lombok.RequiredArgsConstructor; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Repository 
 @RequiredArgsConstructor 
 public class InfrastructureMongoCategoryRepositoryImpl implements DomainCategoryRepositoryPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureMongoCategoryRepositoryImpl.class); 
  
     @Autowired 
     private final InfrastructureMongoCategoryRepository categoryRepository; 
  
     @Override 
     public DomainCategoryEntity save(DomainCategoryEntity category) { 
         if (categoryRepository.existsByName(category.getName())) { 
             throw new RuntimeException("Category name must be unique"); 
         } 
         logger.info("Saving category: {}", category); 
         return categoryRepository.save(category); 
     } 
  
     @Override 
     public DomainCategoryEntity findById(ObjectId id) { 
         logger.info("Finding category by id: {}", id); 
         Optional<DomainCategoryEntity> category = categoryRepository.findById(id); 
         return category.orElseThrow(() -> new RuntimeException("Category not found")); 
     } 
  
     @Override 
     public void deleteById(ObjectId id) { 
         logger.info("Deleting category by id: {}", id); 
         categoryRepository.deleteById(id); 
     } 
  
     @Override 
     public boolean existsById(ObjectId id) { 
         logger.info("Checking existence of category by id: {}", id); 
         return categoryRepository.existsById(id); 
     } 
 } 
 
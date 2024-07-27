package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedProductDTO; 
 import ai.shreds.shared.SharedCategoryDTO; 
 import ai.shreds.shared.SharedSearchCriteria; 
 import ai.shreds.infrastructure.InfrastructureMongoProductRepositoryImpl; 
 import ai.shreds.infrastructure.InfrastructureMongoProductSearchImpl; 
 import ai.shreds.infrastructure.InfrastructureMongoCategorySearchImpl; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.data.domain.Page; 
 import org.springframework.data.domain.PageRequest; 
 import org.springframework.data.domain.Pageable; 
 import java.util.List; 
 import java.util.stream.Collectors; 
  
 @Slf4j 
 @RequiredArgsConstructor 
 public class DomainCatalogQueryService implements DomainProductRepositoryPort, DomainProductSearchPort, DomainCategorySearchPort { 
  
     private final DomainProductRepositoryPort productRepository; 
     private final DomainProductSearchPort productSearch; 
     private final DomainCategorySearchPort categorySearch; 
  
     /** 
      * Retrieves a list of products by category ID. 
      * @param categoryId the ID of the category 
      * @param page the page number 
      * @param size the size of the page 
      * @return list of products in the specified category 
      */ 
     @Override 
     public Page<DomainProductEntity> findByCategoryId(String categoryId, int page, int size) { 
         validateCategoryId(categoryId); 
         Pageable pageable = PageRequest.of(page, size); 
         try { 
             return productRepository.findByCategoryId(categoryId, pageable); 
         } catch (Exception e) { 
             log.error("Error fetching products by category ID: {}", categoryId, e); 
             throw new CustomException("Error fetching products by category ID", e); 
         } 
     } 
  
     /** 
      * Searches for products based on search criteria. 
      * @param searchCriteria the criteria to search products 
      * @param page the page number 
      * @param size the size of the page 
      * @return list of products matching the search criteria 
      */ 
     @Override 
     public Page<DomainProductEntity> searchProducts(DomainSearchCriteria searchCriteria, int page, int size) { 
         validateSearchCriteria(searchCriteria); 
         Pageable pageable = PageRequest.of(page, size); 
         try { 
             return productSearch.searchProducts(searchCriteria, pageable); 
         } catch (Exception e) { 
             log.error("Error searching products with criteria: {}", searchCriteria, e); 
             throw new CustomException("Error searching products", e); 
         } 
     } 
  
     /** 
      * Searches for categories based on search criteria. 
      * @param searchCriteria the criteria to search categories 
      * @param page the page number 
      * @param size the size of the page 
      * @return list of categories matching the search criteria 
      */ 
     @Override 
     public Page<DomainCategoryEntity> searchCategories(DomainSearchCriteria searchCriteria, int page, int size) { 
         validateSearchCriteria(searchCriteria); 
         Pageable pageable = PageRequest.of(page, size); 
         try { 
             return categorySearch.searchCategories(searchCriteria, pageable); 
         } catch (Exception e) { 
             log.error("Error searching categories with criteria: {}", searchCriteria, e); 
             throw new CustomException("Error searching categories", e); 
         } 
     } 
  
     private void validateCategoryId(String categoryId) { 
         if (categoryId == null || categoryId.isEmpty()) { 
             throw new IllegalArgumentException("Category ID must not be null or empty"); 
         } 
     } 
  
     private void validateSearchCriteria(DomainSearchCriteria searchCriteria) { 
         if (searchCriteria == null || (searchCriteria.getName() == null && searchCriteria.getDescription() == null)) { 
             throw new IllegalArgumentException("Search criteria must not be null and at least one field must be specified"); 
         } 
     } 
 } 
  
 class CustomException extends RuntimeException { 
     public CustomException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 }
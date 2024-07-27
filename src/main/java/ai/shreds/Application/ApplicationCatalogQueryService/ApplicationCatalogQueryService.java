package ai.shreds.application; 
  
 import ai.shreds.shared.SharedProductDTO; 
 import ai.shreds.shared.SharedCategoryDTO; 
 import ai.shreds.shared.SharedSearchCriteria; 
 import ai.shreds.domain.DomainCatalogQueryService; 
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainSearchCriteria; 
 import java.util.List; 
 import java.util.stream.Collectors; 
 import lombok.RequiredArgsConstructor; 
 import org.springframework.util.StringUtils; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @RequiredArgsConstructor 
 public class ApplicationCatalogQueryService implements ApplicationGetProductsByCategoryInputPort, ApplicationSearchProductsInputPort, ApplicationSearchCategoriesInputPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationCatalogQueryService.class); 
     private final DomainCatalogQueryService domainCatalogQueryService; 
  
     /** 
      * Retrieves a list of products belonging to a specific category. 
      * @param categoryId The ID of the category to retrieve products for. 
      * @return A list of SharedProductDTO objects. 
      */ 
     @Override 
     public List<SharedProductDTO> getProductsByCategory(String categoryId) { 
         logger.info("Fetching products for category ID: {}", categoryId); 
         validateCategoryId(categoryId); 
         List<DomainProductEntity> domainProducts = domainCatalogQueryService.findByCategoryId(categoryId); 
         return domainProducts.stream() 
                 .map(this::mapToSharedProductDTO) 
                 .collect(Collectors.toList()); 
     } 
  
     /** 
      * Searches for products based on provided search criteria. 
      * @param searchCriteria The search criteria object containing parameters like name and description. 
      * @return A list of SharedProductDTO objects. 
      */ 
     @Override 
     public List<SharedProductDTO> searchProducts(SharedSearchCriteria searchCriteria) { 
         logger.info("Searching products with criteria: {}", searchCriteria); 
         validateSearchCriteria(searchCriteria); 
         DomainSearchCriteria domainSearchCriteria = mapToDomainSearchCriteria(searchCriteria); 
         List<DomainProductEntity> domainProducts = domainCatalogQueryService.searchProducts(domainSearchCriteria); 
         return domainProducts.stream() 
                 .map(this::mapToSharedProductDTO) 
                 .collect(Collectors.toList()); 
     } 
  
     /** 
      * Searches for categories based on provided search criteria. 
      * @param searchCriteria The search criteria object containing parameters like name and description. 
      * @return A list of SharedCategoryDTO objects. 
      */ 
     @Override 
     public List<SharedCategoryDTO> searchCategories(SharedSearchCriteria searchCriteria) { 
         logger.info("Searching categories with criteria: {}", searchCriteria); 
         validateSearchCriteria(searchCriteria); 
         DomainSearchCriteria domainSearchCriteria = mapToDomainSearchCriteria(searchCriteria); 
         List<DomainCategoryEntity> domainCategories = domainCatalogQueryService.searchCategories(domainSearchCriteria); 
         return domainCategories.stream() 
                 .map(this::mapToSharedCategoryDTO) 
                 .collect(Collectors.toList()); 
     } 
  
     private void validateCategoryId(String categoryId) { 
         if (!StringUtils.hasText(categoryId)) { 
             throw new IllegalArgumentException("Category ID must not be empty"); 
         } 
     } 
  
     private void validateSearchCriteria(SharedSearchCriteria searchCriteria) { 
         if (searchCriteria == null || (!StringUtils.hasText(searchCriteria.getName()) && !StringUtils.hasText(searchCriteria.getDescription()))) { 
             throw new IllegalArgumentException("Search criteria must contain at least one non-empty field"); 
         } 
     } 
  
     private SharedProductDTO mapToSharedProductDTO(DomainProductEntity domainProductEntity) { 
         return new SharedProductDTO( 
                 domainProductEntity.getId(), 
                 domainProductEntity.getName(), 
                 domainProductEntity.getDescription(), 
                 domainProductEntity.getCategoryId(), 
                 domainProductEntity.getPrice(), 
                 domainProductEntity.getStock() 
         ); 
     } 
  
     private SharedCategoryDTO mapToSharedCategoryDTO(DomainCategoryEntity domainCategoryEntity) { 
         return new SharedCategoryDTO( 
                 domainCategoryEntity.getId(), 
                 domainCategoryEntity.getName(), 
                 domainCategoryEntity.getDescription() 
         ); 
     } 
  
     private DomainSearchCriteria mapToDomainSearchCriteria(SharedSearchCriteria sharedSearchCriteria) { 
         return new DomainSearchCriteria( 
                 sharedSearchCriteria.getName(), 
                 sharedSearchCriteria.getDescription() 
         ); 
     } 
 } 
 
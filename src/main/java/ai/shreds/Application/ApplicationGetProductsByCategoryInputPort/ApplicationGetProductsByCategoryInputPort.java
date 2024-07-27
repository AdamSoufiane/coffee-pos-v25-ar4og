package ai.shreds.application;

import ai.shreds.shared.SharedProductDTO;
import java.util.List;

/**
 * Interface for retrieving products by category.
 */
public interface ApplicationGetProductsByCategoryInputPort {
    /**
     * Retrieves a list of products belonging to a specific category.
     * 
     * @param categoryId the ID of the category to retrieve products for
     * @return a list of SharedProductDTO objects representing the products in the specified category
     */
    List<SharedProductDTO> getProductsByCategory(String categoryId);
}
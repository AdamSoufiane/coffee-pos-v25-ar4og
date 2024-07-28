package ai.shreds.application;

import ai.shreds.shared.SharedProductListApplicationDTO;
import java.util.List;

/**
 * Interface for retrieving a list of products.
 */
@FunctionalInterface
public interface ApplicationGetProductsListInputPort {
    /**
     * Retrieves a list of all products.
     *
     * @return List of SharedProductListApplicationDTO representing the product details.
     */
    List<SharedProductListApplicationDTO> getProductsList();
}
package ai.shreds.Application;

import ai.shreds.Shared.SharedProductDetailsApplicationDTO;

/**
 * Interface for retrieving detailed information about a specific product by its unique identifier.
 */
public interface ApplicationGetProductDetailsInputPort {

    /**
     * Retrieves detailed information about a specific product by its unique identifier.
     *
     * @param id the unique identifier of the product
     * @return the product details
     */
    SharedProductDetailsApplicationDTO getProductDetails(String id);
}
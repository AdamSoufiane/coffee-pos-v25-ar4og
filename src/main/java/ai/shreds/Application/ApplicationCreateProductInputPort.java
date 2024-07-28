package ai.shreds.Application;

import ai.shreds.Shared.SharedCreateProductApplicationDTO;
import ai.shreds.Shared.SharedProductDetailsApplicationDTO;

/**
 * Interface for creating a new product in the application layer.
 */
public interface ApplicationCreateProductInputPort {
    /**
     * Handles the creation of a new product.
     *
     * @param productRequest the details of the product to be created
     * @return the created product details
     */
    SharedProductDetailsApplicationDTO createProduct(SharedCreateProductApplicationDTO productRequest);
}
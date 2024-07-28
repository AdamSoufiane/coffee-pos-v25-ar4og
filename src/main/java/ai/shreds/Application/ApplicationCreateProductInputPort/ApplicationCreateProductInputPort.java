package ai.shreds.application;

import ai.shreds.shared.SharedCreateProductApplicationDTO;
import ai.shreds.shared.SharedProductDetailsApplicationDTO;

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
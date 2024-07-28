package ai.shreds.Application;

import ai.shreds.Shared.SharedProductDetailsApplicationDTO;
import ai.shreds.Shared.SharedUpdateProductApplicationDTO;

/**
 * ApplicationUpdateProductInputPort defines the contract for updating a product's details.
 */
public interface ApplicationUpdateProductInputPort {

    /**
     * Updates the details of an existing product.
     *
     * @param id the unique identifier of the product
     * @param productRequest the product details to be updated
     * @return the updated product details
     */
    SharedProductDetailsApplicationDTO updateProduct(String id, SharedUpdateProductApplicationDTO productRequest);
}
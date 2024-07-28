package ai.shreds.Application;

import ai.shreds.Shared.SharedCreateProductRequest;
import ai.shreds.Shared.SharedProductDTO;
import ai.shreds.Shared.SharedUpdateProductRequest;
import ai.shreds.Shared.SharedListProductsParams;
import java.util.List;

/**
 * Interface for product service operations in the application layer.
 */
public interface ApplicationProductServicePort {

    /**
     * Retrieves detailed information about a specific product by its ID.
     *
     * @param id the unique identifier of the product
     * @return the product details as a DTO
     */
    SharedProductDTO getProductDetails(String id);

    /**
     * Handles the creation of a new product.
     *
     * @param request the product creation request containing product details
     * @return the created product details as a DTO
     */
    SharedProductDTO createProductDetails(SharedCreateProductRequest request);

    /**
     * Manages the updating of an existing product's details.
     *
     * @param id the unique identifier of the product
     * @param request the product update request containing updated product details
     * @return the updated product details as a DTO
     */
    SharedProductDTO updateProductDetails(String id, SharedUpdateProductRequest request);

    /**
     * Handles the deletion of a product by its ID.
     *
     * @param id the unique identifier of the product
     */
    void deleteProductDetails(String id);

    /**
     * Retrieves a list of products with optional filters for category ID, price range, and stock availability.
     *
     * @param params the parameters for filtering the product list
     * @return the list of products matching the filters as DTOs
     */
    List<SharedProductDTO> listProducts(SharedListProductsParams params);
}
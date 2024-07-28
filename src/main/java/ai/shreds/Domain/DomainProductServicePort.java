package ai.shreds.Domain;

import ai.shreds.Shared.SharedCreateProductRequest;
import ai.shreds.Shared.SharedListProductsParams;
import ai.shreds.Shared.SharedUpdateProductRequest;
import java.util.List;

/**
 * Interface for the Product Service in the domain layer.
 * Defines the contract for product-related operations.
 */
public interface DomainProductServicePort {

    /**
     * Retrieves detailed information about a specific product by its ID.
     *
     * @param id the unique identifier of the product
     * @return the product details
     * @throws NotFoundException if the product is not found
     */
    DomainProductEntity getProductDetails(String id) throws NotFoundException;

    /**
     * Handles the creation of a new product.
     *
     * @param request the product creation request
     * @return the created product
     * @throws BadRequestException if the input data is invalid
     */
    DomainProductEntity createProductDetails(SharedCreateProductRequest request) throws BadRequestException;

    /**
     * Manages the updating of an existing product's details.
     *
     * @param id the unique identifier of the product
     * @param request the product update request
     * @return the updated product
     * @throws NotFoundException if the product is not found
     * @throws BadRequestException if the input data is invalid
     */
    DomainProductEntity updateProductDetails(String id, SharedUpdateProductRequest request) throws NotFoundException, BadRequestException;

    /**
     * Handles the deletion of a product by its ID.
     *
     * @param id the unique identifier of the product
     * @throws NotFoundException if the product is not found
     */
    void deleteProductDetails(String id) throws NotFoundException;

    /**
     * Retrieves a list of products with optional filters for category ID, price range, and stock availability.
     *
     * @param params the product listing parameters
     * @return the list of products
     */
    List<DomainProductEntity> listProducts(SharedListProductsParams params);
}
package ai.shreds.Domain;

import java.util.List;

/**
 * Interface for CRUD operations on the Product entity in the Catalog Database.
 */
public interface DomainProductRepositoryPort {
    /**
     * Finds a product by its unique identifier.
     *
     * @param id the unique identifier of the product
     * @return the product entity
     */
    DomainProductEntity findById(String id);

    /**
     * Finds all products in the Catalog Database.
     *
     * @return a list of product entities
     */
    List<DomainProductEntity> findAll();

    /**
     * Saves a new or updated product to the Catalog Database.
     *
     * @param product the product entity to be saved
     * @return the saved product entity
     */
    DomainProductEntity save(DomainProductEntity product);

    /**
     * Deletes a product by its unique identifier.
     *
     * @param id the unique identifier of the product to be deleted
     */
    void deleteById(String id);
}
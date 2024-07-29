package ai.shreds.domain;

import java.util.UUID;

/**
 * DomainOrderRepositoryPort is an interface that defines the methods for interacting with the Order entity in the database.
 */
public interface DomainOrderRepositoryPort {
    /**
     * Saves the order entity to the database.
     *
     * @param order the order entity to be saved
     */
    void save(DomainOrderEntity order);

    /**
     * Finds an order by its ID.
     *
     * @param orderId the unique identifier of the order
     * @return the order entity if found, otherwise null
     */
    DomainOrderEntity findById(UUID orderId);
}
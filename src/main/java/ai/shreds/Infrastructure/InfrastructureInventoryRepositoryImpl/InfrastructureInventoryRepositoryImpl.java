package ai.shreds.infrastructure;

import ai.shreds.domain.DomainInventoryItemEntity;
import ai.shreds.domain.DomainInventoryRepositoryPort;
import ai.shreds.domain.DomainMapperDTOToInventoryItem;
import ai.shreds.domain.DomainMapperInventoryItemToDTO;
import javax.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Implementation of the DomainInventoryRepositoryPort interface.
 * This class interacts with the database to perform CRUD operations on InventoryItem entities.
 */
@Repository
@Transactional
public class InfrastructureInventoryRepositoryImpl implements DomainInventoryRepositoryPort {

    private final JpaInventoryRepository jpaInventoryRepository;
    private final DomainMapperDTOToInventoryItem dtoToInventoryItemMapper;
    private final DomainMapperInventoryItemToDTO inventoryItemToDTOMapper;

    @Autowired
    public InfrastructureInventoryRepositoryImpl(JpaInventoryRepository jpaInventoryRepository,
                                                 DomainMapperDTOToInventoryItem dtoToInventoryItemMapper,
                                                 DomainMapperInventoryItemToDTO inventoryItemToDTOMapper) {
        this.jpaInventoryRepository = jpaInventoryRepository;
        this.dtoToInventoryItemMapper = dtoToInventoryItemMapper;
        this.inventoryItemToDTOMapper = inventoryItemToDTOMapper;
    }

    /**
     * Finds an inventory item by its unique identifier.
     * @param itemId The unique identifier for the inventory item.
     * @return The found DomainInventoryItemEntity or throws EntityNotFoundException if not found.
     */
    @Override
    public DomainInventoryItemEntity findByItemId(String itemId) {
        return jpaInventoryRepository.findById(itemId)
                .map(inventoryItem -> dtoToInventoryItemMapper.map(inventoryItem))
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + itemId));
    }

    /**
     * Saves or updates an inventory item in the database.
     * @param domainInventoryItemEntity The inventory item entity to be saved or updated.
     */
    @Override
    public void save(DomainInventoryItemEntity domainInventoryItemEntity) {
        InventoryItem inventoryItem = inventoryItemToDTOMapper.map(domainInventoryItemEntity);
        jpaInventoryRepository.save(inventoryItem);
    }
}
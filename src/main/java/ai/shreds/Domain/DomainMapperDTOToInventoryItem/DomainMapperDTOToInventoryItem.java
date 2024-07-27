package ai.shreds.domain;

import ai.shreds.shared.SharedInventoryItemDTO;
import ai.shreds.domain.DomainInventoryItemEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This class is responsible for mapping SharedInventoryItemDTO objects to DomainInventoryItemEntity objects.
 */
@RequiredArgsConstructor
public class DomainMapperDTOToInventoryItem {

    /**
     * Maps a SharedInventoryItemDTO object to a DomainInventoryItemEntity object.
     *
     * @param itemDTO the SharedInventoryItemDTO object to be mapped
     * @return the mapped DomainInventoryItemEntity object
     */
    public DomainInventoryItemEntity map(@NonNull SharedInventoryItemDTO itemDTO) {
        return new DomainInventoryItemEntity(
                itemDTO.getItemId(),
                itemDTO.getQuantity(),
                itemDTO.getLocation()
        );
    }
}
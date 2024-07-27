package ai.shreds.domain;

import ai.shreds.shared.SharedInventoryItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper class to convert DomainInventoryItemEntity to SharedInventoryItemDTO.
 */
@Component
@RequiredArgsConstructor
public class DomainMapperInventoryItemToDTO {

    /**
     * Maps a DomainInventoryItemEntity to a SharedInventoryItemDTO.
     *
     * @param domainInventoryItem the domain entity to be mapped
     * @return the mapped SharedInventoryItemDTO
     */
    public SharedInventoryItemDTO map(DomainInventoryItemEntity domainInventoryItem) {
        if (domainInventoryItem == null) {
            throw new IllegalArgumentException("DomainInventoryItemEntity cannot be null");
        }

        return SharedInventoryItemDTO.builder()
                .itemId(domainInventoryItem.getItemId())
                .quantity(domainInventoryItem.getQuantity())
                .location(domainInventoryItem.getLocation())
                .build();
    }
}
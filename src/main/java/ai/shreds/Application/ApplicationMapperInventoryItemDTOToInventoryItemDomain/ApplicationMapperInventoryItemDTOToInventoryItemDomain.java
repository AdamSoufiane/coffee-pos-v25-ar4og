package ai.shreds.application;

import ai.shreds.domain.DomainInventoryItemEntity;
import ai.shreds.shared.SharedInventoryItemDTO;
import java.util.Objects;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApplicationMapperInventoryItemDTOToInventoryItemDomain {

    public DomainInventoryItemEntity map(SharedInventoryItemDTO itemDTO) {
        if (Objects.isNull(itemDTO) || !StringUtils.hasLength(itemDTO.getItemId()) || itemDTO.getQuantity() == null || !StringUtils.hasLength(itemDTO.getLocation())) {
            throw new IllegalArgumentException("One or more fields in the SharedInventoryItemDTO are null or empty.");
        }
        return new DomainInventoryItemEntity(
                itemDTO.getItemId(),
                itemDTO.getQuantity(),
                itemDTO.getLocation()
        );
    }
}
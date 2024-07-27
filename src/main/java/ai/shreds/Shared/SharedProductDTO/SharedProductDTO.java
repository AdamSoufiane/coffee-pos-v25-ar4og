package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ai.shreds.domain.DomainProductEntity;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object for Product details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedProductDTO {
    private String id;
    @Size(max = 255)
    private String name;
    private String description;
    private String categoryId;
    private Double price;
    private Integer stock;

    /**
     * Converts a DomainProductEntity to SharedProductDTO.
     *
     * @param entity the DomainProductEntity to convert
     * @return the corresponding SharedProductDTO
     */
    public static SharedProductDTO fromEntity(DomainProductEntity entity) {
        return new SharedProductDTO(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getCategoryId(),
            entity.getPrice(),
            entity.getStock()
        );
    }

    /**
     * Converts this SharedProductDTO to a DomainProductEntity.
     *
     * @return the corresponding DomainProductEntity
     */
    public DomainProductEntity toEntity() {
        return new DomainProductEntity(
            this.id != null ? this.id : null,
            this.name != null ? this.name : null,
            this.description != null ? this.description : null,
            this.categoryId != null ? this.categoryId : null,
            this.price != null ? this.price : null,
            this.stock != null ? this.stock : null
        );
    }
}
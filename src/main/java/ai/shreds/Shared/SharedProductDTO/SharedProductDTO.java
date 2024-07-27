package ai.shreds.shared;

import ai.shreds.domain.DomainProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a product in the catalog.
 * This class is used to transfer product data between different layers of the application.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedProductDTO {
    private String id;
    private String name;
    private String description;
    private String categoryId;
    private double price;
    private int stock;

    /**
     * Maps this DTO to a DomainProductEntity object.
     *
     * @return a DomainProductEntity object with the same data as this DTO.
     */
    public DomainProductEntity internalMapper() {
        DomainProductEntity entity = new DomainProductEntity();
        entity.setId(this.id != null ? this.id : "");
        entity.setName(this.name != null ? this.name : "");
        entity.setDescription(this.description != null ? this.description : "");
        entity.setCategoryId(this.categoryId != null ? this.categoryId : "");
        entity.setPrice(this.price);
        entity.setStock(this.stock);
        return entity;
    }
}
package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SharedProductDetailsDomainDTO is a Data Transfer Object (DTO) that represents detailed information about a product in the domain layer.
 * This class contains fields for id, name, description, categoryId, price, and stock.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedProductDetailsDomainDTO {
    private String id;
    private String name;
    private String description;
    private String categoryId;
    private Double price;
    private Integer stock;
}
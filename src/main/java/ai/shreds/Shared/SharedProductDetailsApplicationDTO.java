package ai.shreds.Shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for transferring product details between different layers of the application.
 * This DTO includes fields representing the product's ID, name, description, category ID, price, and stock level.
 *
 * Note: Ensure that Lombok annotations are correctly used and configured.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharedProductDetailsApplicationDTO {
    private String id;
    private String name;
    private String description;
    private String categoryId;
    private Double price;
    private Integer stock;
}
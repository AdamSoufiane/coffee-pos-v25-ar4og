package ai.shreds.Domain;

import ai.shreds.Shared.SharedProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * Represents a product within the catalog.
 */
@Document(collection = "products")
public class DomainProductEntity {
    /** Unique identifier for the product. */
    @Id
    @NotNull(message = "Product ID cannot be null")
    private String id;
    /** Name of the product. */
    @Size(min = 1, max = 100, message = "Product name must be between 1 and 100 characters")
    private String name;
    /** Description of the product. */
    @Size(max = 500, message = "Product description cannot exceed 500 characters")
    private String description;
    /** Identifier for the category this product belongs to. */
    @NotNull(message = "Category ID cannot be null")
    private String categoryId;
    /** Price of the product. */
    @NotNull(message = "Product price cannot be null")
    private Double price;
    /** Stock level of the product. */
    @NotNull(message = "Product stock cannot be null")
    private Integer stock;
    /** Version field for optimistic locking. */
    @Version
    private Long version;

    /**
     * Maps SharedProductDTO to DomainProductEntity.
     * @param dto the SharedProductDTO object
     * @return the DomainProductEntity object
     */
    public static DomainProductEntity fromDTO(SharedProductDTO dto) {
        return DomainProductEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .categoryId(dto.getCategoryId())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
    }

    /**
     * Maps DomainProductEntity to SharedProductDTO.
     * @return the SharedProductDTO object
     */
    public SharedProductDTO toDTO() {
        return SharedProductDTO.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .categoryId(this.categoryId)
                .price(this.price)
                .stock(this.stock)
                .build();
    }

    /**
     * Validates the product data before creation or update.
     * Ensures product names are unique, prices are greater than zero, and stock levels are non-negative.
     */
    public void validateProductData() {
        if (this.price <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero.");
        }
        if (this.stock < 0) {
            throw new IllegalArgumentException("Stock level must be non-negative.");
        }
        // Additional validation logic can be added here
    }

    // Add constructor validation
    public DomainProductEntity(String id, String name, String description, String categoryId, Double price, Integer stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
        this.stock = stock;
        validateProductData();
    }
}
package ai.shreds.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Represents a product entity within the domain layer.
 * Encapsulates the attributes of a product such as id, name, description, categoryId, price, and stock.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomainProductEntity {

    @NotNull(message = "id cannot be null")
    private String id;

    @NotNull(message = "name cannot be null")
    @Size(min = 1, message = "name cannot be empty")
    private String name;

    @NotNull(message = "description cannot be null")
    @Size(min = 1, message = "description cannot be empty")
    private String description;

    @NotNull(message = "categoryId cannot be null")
    private String categoryId;

    @NotNull(message = "price cannot be null")
    private Double price;

    @NotNull(message = "stock cannot be null")
    private Integer stock;

    /**
     * Validates that the price is a positive value.
     * @throws IllegalArgumentException if the price is not positive.
     */
    public void validatePrice() {
        if (this.price <= 0) {
            throw new IllegalArgumentException("Price must be a positive value.");
        }
    }

    /**
     * Validates that the stock level is a non-negative integer.
     * @throws IllegalArgumentException if the stock level is negative.
     */
    public void validateStock() {
        if (this.stock < 0) {
            throw new IllegalArgumentException("Stock level must be a non-negative integer.");
        }
    }

    public void setDescription(String description) {
        this.description = Objects.requireNonNull(description, "description cannot be null");
        validateDescription();
    }

    public void setPrice(Double price) {
        this.price = Objects.requireNonNull(price, "price cannot be null");
        validatePrice();
    }

    public void setStock(Integer stock) {
        this.stock = Objects.requireNonNull(stock, "stock cannot be null");
        validateStock();
    }

    private void validateDescription() {
        if (this.description == null || this.description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
    }
}
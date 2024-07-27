package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Represents a product within the catalog.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainProductEntity {
    /**
     * Unique identifier for the product.
     */
    @NotNull
    private String id;

    /**
     * Name of the product.
     */
    @Size(max = 255)
    private String name;

    /**
     * Description of the product.
     */
    @Size(max = 255)
    private String description;

    /**
     * Identifier for the category this product belongs to.
     */
    private String categoryId;

    /**
     * Price of the product.
     */
    private double price;

    /**
     * Stock level of the product.
     */
    private int stock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainProductEntity that = (DomainProductEntity) o;
        return Double.compare(that.price, price) == 0 && stock == that.stock && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, categoryId, price, stock);
    }

    @Override
    public String toString() {
        return "DomainProductEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
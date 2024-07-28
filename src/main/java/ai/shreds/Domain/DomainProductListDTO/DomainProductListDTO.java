package ai.shreds.domain;

import java.io.Serializable;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Product List in the domain layer.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class DomainProductListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the product.
     */
    @Nonnull
    private String id;

    /**
     * Name of the product.
     */
    @Nonnull
    private String name;

    /**
     * Description of the product.
     */
    @Nonnull
    private String description;

    /**
     * Identifier for the category this product belongs to.
     */
    @Nonnull
    private String categoryId;

    /**
     * Price of the product.
     */
    private Double price;

    /**
     * Stock level of the product.
     */
    private Integer stock;
}
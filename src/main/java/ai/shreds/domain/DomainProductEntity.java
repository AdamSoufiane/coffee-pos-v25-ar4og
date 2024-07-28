package ai.shreds.domain;

import ai.shreds.shared.SharedCreateProductRequest;
import ai.shreds.shared.SharedUpdateProductRequest;
import ai.shreds.shared.SharedProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomainProductEntity {
    @NotNull(message = "Product ID cannot be null")
    private String id;
    @Size(min = 1, max = 100, message = "Product name must be between 1 and 100 characters")
    private String name;
    @Size(max = 500, message = "Product description cannot exceed 500 characters")
    private String description;
    @NotNull(message = "Category ID cannot be null")
    private String categoryId;
    @NotNull(message = "Product price cannot be null")
    private Double price;
    @NotNull(message = "Product stock cannot be null")
    private Integer stock;
    @Version
    private Long version;

    public static DomainProductEntity fromDTO(SharedProductDTO dto) {
        return dto.toEntity();
    }

    public SharedProductDTO toDTO() {
        return SharedProductDTO.fromEntity(this);
    }

    public static DomainProductEntity fromCreateRequest(SharedCreateProductRequest request) {
        return DomainProductEntity.builder()
                .id(UUID.randomUUID().toString()) // Generate a new UUID for the product
                .name(request.getName())
                .description(request.getDescription())
                .categoryId(request.getCategoryId())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
    }

    public static DomainProductEntity fromUpdateRequest(SharedUpdateProductRequest request) {
        return DomainProductEntity.builder()
                .id(null) // ID will be retained or assigned later
                .name(request.getName())
                .description(request.getDescription())
                .categoryId(request.getCategoryId())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
    }

    public void updateFromRequest(SharedUpdateProductRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.categoryId = request.getCategoryId();
        this.price = request.getPrice();
        this.stock = request.getStock();
    }

    public void validateProductData() {
        if (this.price <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero.");
        }
        if (this.stock < 0) {
            throw new IllegalArgumentException("Stock level must be non-negative.");
        }
    }
}
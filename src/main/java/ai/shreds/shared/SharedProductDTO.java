package ai.shreds.shared;

import ai.shreds.domain.DomainProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SharedProductDTO {
    @NotEmpty(message = "Product ID must not be empty")
    private String id;
    @NotBlank(message = "Product name must not be blank")
    @Size(min = 1, max = 255)
    private String name;
    @Size(max = 512)
    private String description;
    @NotEmpty(message = "Category ID must not be empty")
    private String categoryId;
    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be positive")
    private Double price;
    @NotNull(message = "Stock must not be null")
    @PositiveOrZero(message = "Stock must be positive or zero")
    private Integer stock;

    public static SharedProductDTO fromEntity(DomainProductEntity entity) {
        return SharedProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .categoryId(entity.getCategoryId())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .build();
    }

    public DomainProductEntity toEntity() {
        return DomainProductEntity.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .categoryId(this.categoryId)
                .price(this.price)
                .stock(this.stock)
                .build();
    }
}
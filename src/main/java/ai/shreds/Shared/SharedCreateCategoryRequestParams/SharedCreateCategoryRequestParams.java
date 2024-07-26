package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharedCreateCategoryRequestParams {
    @NotEmpty(message = "Category name must not be empty")
    private String name;
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
}
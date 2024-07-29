package ai.shreds.Shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedCreateCategoryResponseDTO {
    private String id;
    private String name;
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
}

package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedCreateCategoryRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String description;
}

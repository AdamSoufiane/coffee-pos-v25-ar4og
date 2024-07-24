
package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedDeleteCategoryRequest {
    @NotEmpty(message = "Category ID cannot be empty")
    private String id;
}

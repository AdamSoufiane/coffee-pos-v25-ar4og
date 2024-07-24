
package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response returned after successfully deleting a category.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedDeleteCategoryResponse {
    private String message;
}

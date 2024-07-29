package ai.shreds.Shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for encapsulating the response message when a product is successfully deleted.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedDeleteProductResponseApplicationDTO {
    /**
     * Success message indicating that the product has been deleted.
     */
    private String message;
}
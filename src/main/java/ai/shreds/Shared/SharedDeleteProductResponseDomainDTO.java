package ai.shreds.Shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) that represents the response message after a product is deleted.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedDeleteProductResponseDomainDTO {
    /**
     * The success or error message related to the deletion operation.
     */
    private String message;
}
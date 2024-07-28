package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for the response message of a delete product operation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainDeleteProductResponseDTO {
    /**
     * Message indicating the result of the delete operation.
     */
    private String message;
}
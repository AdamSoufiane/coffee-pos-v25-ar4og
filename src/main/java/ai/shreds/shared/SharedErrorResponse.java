package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Data Transfer Object (DTO) representing an error response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedErrorResponse {
    /**
     * The error message.
     */
    private String error;
}
package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Data Transfer Object (DTO) for encapsulating error messages returned by the API endpoints.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedErrorResponse {
    private String error;
}

package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This package contains shared Data Transfer Objects (DTOs) used across different layers of the application.
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

package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for representing an error response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedErrorResponse {
    /**
     * The error message to be returned in the response.
     */
    private String error;

    /**
     * Sets the error message.
     * @param error the error message
     * @throws IllegalArgumentException if the error message is null or empty
     */
    public void setError(String error) {
        if (error == null || error.trim().isEmpty()) {
            throw new IllegalArgumentException("Error message cannot be null or empty");
        }
        this.error = error;
    }
}
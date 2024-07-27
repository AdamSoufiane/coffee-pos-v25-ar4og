package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) that encapsulates the response details of an order creation request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedOrderResponseDTO {
    /**
     * UUID representing the unique identifier for the created order.
     */
    private UUID orderId;
    /**
     * String indicating the current status of the order (e.g., 'CREATED').
     */
    private String status;
    /**
     * String containing a success message or any other relevant information.
     */
    private String message;
}